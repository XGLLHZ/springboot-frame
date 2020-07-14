package org.huangzi.main.authority.config;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.huangzi.main.authority.entity.*;
import org.huangzi.main.authority.mapper.SYSPermMapper;
import org.huangzi.main.authority.mapper.SYSTokenMapper;
import org.huangzi.main.authority.service.SYSPermRoleService;
import org.huangzi.main.authority.service.SYSTokenService;
import org.huangzi.main.authority.service.SYSUserService;
import org.huangzi.main.common.dto.ExceptionDto;
import org.huangzi.main.common.dto.TokenDto;
import org.huangzi.main.common.utils.APIResponse;
import org.huangzi.main.common.utils.ConstConfig;
import org.huangzi.main.common.service.OnlineUserService;
import org.huangzi.main.common.utils.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author: XGLLHZ
 * @date: 2019/8/23 9:46
 * @description: spring security权限配置
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    @Qualifier("sysUserService")
    private SYSUserService sysUserService;  //系统用户信息-账号、角色

    @Autowired
    private FilterRequestRole filterRequestRole;   //请求信息-url、角色

    @Autowired
    private UrlRoleAccessDecisionManager urlRoleAccessDecisionManager;   //当前登录用户的角色与请求资源需要的角色对比

    @Autowired
    private PermissionAccessDeniedHandler permissionAccessDeniedHandler;   //授权失败-权限不足

    @Autowired
    private SYSTokenMapper sysTokenMapper;

    @Autowired
    @Qualifier("sysTokenService")
    private SYSTokenService sysTokenService;

    @Autowired
    private SYSPermMapper sysPermMapper;

    @Autowired
    private OnlineUserService onlineUserService;

    @Autowired
    private SYSPermRoleService sysPermRoleService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //获取当前登录用户，并用用户添加时的加密规则对用户密码解密（之前的加密规则已被 spring security 抛弃）
        //注：用户新增（即注册）时，需要对用户密码用 BCryptPasswordEncoder 类中的方法加密
        auth.userDetailsService(sysUserService)
                .passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeRequests()
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O o) {
                        o.setSecurityMetadataSource(filterRequestRole);
                        o.setAccessDecisionManager(urlRoleAccessDecisionManager);
                        return o;
                    }
                }).and().formLogin()
                .loginPage("/admin/user/login_code")   //在此接口中系统会返回一个 recode（105），前端根此返回码跳转到登录页
                .loginProcessingUrl("/admin/user/login")   //登录接口 实际上没有此接口，登录逻辑的处理 spring security 会自动处理
                .usernameParameter("username")   //系统-用户实体中的用户账号属性
                .passwordParameter("password")   //系统-用户实体中的密码账号属性
                .failureHandler(new AuthenticationFailureHandler() {   //授权或决策失败时
                    @Override
                    public void onAuthenticationFailure(HttpServletRequest request,
                                                        HttpServletResponse response,
                                                        AuthenticationException e) throws IOException, ServletException {
                        response.setContentType("application/json;charset=utf-8");
                        APIResponse apiResponse = new APIResponse();
                        //用户名或密码错误时返回 101
                        if (e instanceof UsernameNotFoundException || e instanceof BadCredentialsException) {
                            apiResponse.setRecode(ConstConfig.RE_USERNAME_USERPWD_ERROR_CODE);
                            apiResponse.setRemsg(ConstConfig.RE_USERNAME_USERPWD_ERROR_MESSAGE);
                        } else {   //其它异常时返回 102
                            apiResponse.setRecode(ConstConfig.RE_LOGIN_ERROR_CODE);
                            apiResponse.setRemsg(ConstConfig.RE_LOGIN_ERROR_MESSAGE);
                        }
                        //ObjectMapper为阿里推出的 jackson 依赖中的类，可将对象转化为字符串
                        ObjectMapper objectMapper = new ObjectMapper();
                        PrintWriter out = response.getWriter();
                        out.write(objectMapper.writeValueAsString(apiResponse));
                        out.flush();
                        out.close();
                    }
                })
                .successHandler(new AuthenticationSuccessHandler() {   //授权或决策成功时
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest request,
                                                        HttpServletResponse response,
                                                        Authentication authentication) throws IOException, ServletException {
                        response.setContentType("application/json;charset=utf-8");

                        //将用户信息和登录凭证（token）返回
                        SYSUser sysUser = SYSUserConfig.getCurrentUser();
                        //登陆成功后   创建或修改token
                        TokenDto tokenDto = new TokenDto();
                        tokenDto.setUserId(sysUser.getId());
                        tokenDto.setUserName(sysUser.getUsername());
                        String token = TokenUtil.createToken(tokenDto, "springboot-frame");
                        //查找用户是否有登录历史
                        SYSToken sysToken = sysTokenMapper.selectOne(
                                new QueryWrapper<SYSToken>().eq("user_id", sysUser.getId())
                        );
                        //如果有登录历史，但 token 过期，则为其更新 token
                        if (sysToken != null && TokenUtil.checkToken(sysToken.getToken()) == 1002) {
                            sysToken.setToken(token);
                            sysTokenMapper.updateById(sysToken);
                        } else if (sysToken != null && TokenUtil.checkToken(sysToken.getToken()) == 1001){
                            //有登录历史，但 token 未过期
                            token = sysToken.getToken();
                        } else {
                            //若无登录历史  则创建新的token
                            SYSToken sysToken1 = new SYSToken();
                            sysToken1.setUserId(sysUser.getId());
                            sysToken1.setToken(token);
                            sysTokenMapper.insert(sysToken1);
                        }

                        //根据用户权限构建菜单树
                        List<Integer> ids = sysUser.getList().stream().map(SYSRole::getId).collect(Collectors.toList());
                        List<SYSPermRole> list = sysPermRoleService.list(new QueryWrapper<SYSPermRole>()
                                .eq("delete_flag", ConstConfig.DELETE_FLAG_ZONE)
                                .in("role_id", ids));
                        if (list == null) {
                            throw new ExceptionDto(ConstConfig.RE_ERROR_CODE, ConstConfig.RE_ERROR_MESSAGE);
                        }
                        List<Integer> ids1 = list.stream().map(SYSPermRole::getPermId).distinct().collect(Collectors.toList());
                        List<SYSPermission> list1 = sysPermMapper.selectBatchIds(ids1)
                                .stream()
                                .sorted(Comparator.comparing(SYSPermission::getPermSort).reversed())
                                .collect(Collectors.toList());
                        List<SYSPermission> menuList = new ArrayList<>();
                        for (SYSPermission sysPermission : getRootNode(list1)) {
                            menuList.add(buildTree(sysPermission, list1));
                        }

                        //将在线用户信息保存于 redis 中
                        onlineUserService.saveOnlineUserInfo(request, sysUser.getId(), sysUser.getUsername(), sysUser.getUserType());

                        Map<String, Object> map = new HashMap<>();
                        map.put("dataInfo", sysUser);
                        map.put("token", token);
                        map.put("menuList", menuList);

                        ObjectMapper objectMapper = new ObjectMapper();
                        PrintWriter out = response.getWriter();
                        out.write(objectMapper.writeValueAsString(new APIResponse(map)));
                        out.flush();
                        out.close();
                    }
                })
                .and()
                .logout()
                .permitAll()
                .and()
                .csrf()
                .disable()
                .exceptionHandling()
                .accessDeniedHandler(permissionAccessDeniedHandler);
        httpSecurity.csrf().disable();
    }

    /**
     * 获取根节点
     * @param list
     * @return
     */
    private List<SYSPermission> getRootNode(List<SYSPermission> list) {
        List<SYSPermission> list1 = new ArrayList<>();
        for (SYSPermission sysPermission : list) {
            if (sysPermission.getParentId().equals(0)) {
                list1.add(sysPermission);
            }
        }
        return list1;
    }

    /**
     * 构建每一个根节点的树
     * @param sysPermission
     * @param list
     * @return
     */
    private SYSPermission buildTree(SYSPermission sysPermission, List<SYSPermission> list) {
        List<SYSPermission> list1 = new ArrayList<>();
        for (SYSPermission sysPermission1 : list) {
            if (sysPermission1.getParentId().equals(sysPermission.getId())) {
                list1.add(buildTree(sysPermission1, list));
            }
        }
        sysPermission.setChildrenList(list1);
        return sysPermission;
    }

}
