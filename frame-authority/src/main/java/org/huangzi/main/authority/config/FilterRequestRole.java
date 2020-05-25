package org.huangzi.main.authority.config;

import org.huangzi.main.authority.entity.SYSPermission;
import org.huangzi.main.authority.entity.SYSRole;
import org.huangzi.main.authority.mapper.SYSPermMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;
import java.util.List;

/**
 * @author: XGLLHZ
 * @date: 2019/8/22 20:48
 * @description: 权限资源获取-拦截请求url，并根据url获取访问此资源所需的角色
 */
@Component
public class FilterRequestRole implements FilterInvocationSecurityMetadataSource {

    private static final Logger log = LoggerFactory.getLogger(FilterRequestRole.class);

    @Autowired
    private SYSPermMapper sysPermMapper;

    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        //获取请求地址，如：admin/user/list
        String requestUrl = ((FilterInvocation) o).getRequestUrl();
        //如果请求地址为 /admin/user/login || /admin/user/login_code 则放行
        if ("/admin/user/login".equals(requestUrl) || "/admin/user/login_code".equals(requestUrl)) {
            //log.info("当前请求地址：" + requestUrl + "，为登录或获取登录码请求，不校验权限");
            return null;
        }
        //log.info("当前请求地址：" + requestUrl + "，需要校验权限");
        //获取所有权限（数据库中所有的url）及其对应的角色列表
        List<SYSPermission> list = sysPermMapper.allUrlRole();
        AntPathMatcher antPathMatcher = new AntPathMatcher();
        for (SYSPermission sysPermission : list) {
            //match()方法可以比较地址是否相同
            if (antPathMatcher.match(sysPermission.getPermUrl(), requestUrl)) {
                List<SYSRole> sysPermissionList = sysPermission.getRoles();
                String[] roleArrays = new String[sysPermissionList.size()];
                for (int i = 0; i < sysPermissionList.size(); i++) {
                    roleArrays[i] = sysPermissionList.get(i).getRoleNamey();
                }
                return SecurityConfig.createList(roleArrays);
            }
        }
        //没有匹配上的地址则单独创建一个登录 的角色集合（实际上没有这个角色），后面会对这个角色单独处理
        //log.info("当前请求地址：" + requestUrl + "，没有匹配到权限，则赋给默认登录 LOGIN_ROLE 角色");
        return SecurityConfig.createList("LOGIN_ROLE");
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }

}
