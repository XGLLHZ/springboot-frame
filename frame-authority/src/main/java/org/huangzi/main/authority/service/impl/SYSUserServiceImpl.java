package org.huangzi.main.authority.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.huangzi.main.authority.entity.SYSUserRole;
import org.huangzi.main.common.dto.ExceptionDto;
import org.huangzi.main.common.utils.APIResponse;
import org.huangzi.main.common.utils.ConstConfig;
import org.huangzi.main.authority.entity.SYSRole;
import org.huangzi.main.authority.entity.SYSUser;
import org.huangzi.main.authority.mapper.SYSTokenMapper;
import org.huangzi.main.authority.mapper.SYSUserMapper;
import org.huangzi.main.authority.mapper.SYSUserRoleMapper;
import org.huangzi.main.authority.service.SYSTokenService;
import org.huangzi.main.authority.service.SYSUserRoleService;
import org.huangzi.main.authority.service.SYSUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author: XGLLHZ
 * @date: 2019/8/20 11:40
 * @description: 系统-用户-事务层实现类
 */
@Service
@Primary
@Component("sysUserService")
public class SYSUserServiceImpl extends ServiceImpl<SYSUserMapper, SYSUser> implements SYSUserService {

    @Autowired
    SYSUserMapper sysUserMapper;

    @Autowired
    SYSUserRoleMapper sysUserRoleMapper;

    @Autowired
    SYSTokenMapper sysTokenMapper;

    @Autowired
    SYSTokenService sysTokenService;

    @Autowired
    SYSUserRoleService sysUserRoleService;

    @Override
    public APIResponse list(SYSUser sysUser) {
        Page<SYSUser> page = new Page<>(sysUser.getCurrentPage(), sysUser.getPageSize());
        List<SYSUser> list = sysUserMapper.list(page, sysUser);
        Integer total = sysUserMapper.total(sysUser);
        Map<String, Object> data = new HashMap<>();
        data.put("dataList", list);
        data.put("total", total);
        return new APIResponse(data);
    }

    @Override
    public APIResponse get(SYSUser sysUser) {
        SYSUser sysUser1 = sysUserMapper.selectById(sysUser.getId());
        if (sysUser1 == null) {
            return new APIResponse(ConstConfig.RE_NO_EXIST_ERROR_CODE, ConstConfig.RE_NO_EXIST_ERROR_MESSAGE);
        }
        List<SYSRole> list = sysUserMapper.userRoleList(sysUser.getId());
        if (list == null) {
            return new APIResponse(ConstConfig.RE_ERROR_CODE, ConstConfig.RE_ERROR_MESSAGE);
        }
        List<Integer> ids = list.stream()
                .map(SYSRole::getId).collect(Collectors.toList());
        Map<String, Object> map = new HashMap<>(1);
        sysUser1.setRoleIds(ids);
        sysUser1.setList(list);
        map.put(ConstConfig.DATA_INFO, sysUser1);
        return new APIResponse(map);
    }

    @Override
    @Transactional
    public APIResponse insert(SYSUser sysUser) {
        SYSUser sysUser1 = sysUserMapper.selectOne(
                new QueryWrapper<SYSUser>().eq("username", sysUser.getUsername()));
        if (sysUser1 != null) {
            return new APIResponse(ConstConfig.RE_NAME_ALREADY_EXIST_ERROR_CODE,
                    ConstConfig.RE_NAME_ALREADY_EXIST_ERROR_MESSAGE);
        } else {
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            String password = bCryptPasswordEncoder.encode(sysUser.getPassword());
            sysUser.setPassword(password);
            int res = sysUserMapper.insert(sysUser);
            if (res <= 0) {
                throw new ExceptionDto(ConstConfig.RE_ERROR_CODE, ConstConfig.RE_ERROR_MESSAGE);
            }
            List<SYSUserRole> list = new ArrayList<>();
            if (sysUser.getRoleIds() != null && sysUser.getRoleIds().size() > 0) {
                SYSUserRole sysUserRole;
                for (int roleId : sysUser.getRoleIds()) {
                    sysUserRole = new SYSUserRole();
                    sysUserRole.setUserId(sysUser.getId());
                    sysUserRole.setRoleId(roleId);
                    list.add(sysUserRole);
                }
                boolean result = sysUserRoleService.saveBatch(list);
                if (!result) {
                    throw new ExceptionDto(ConstConfig.RE_ERROR_CODE, ConstConfig.RE_ERROR_MESSAGE);
                } else {
                    return new APIResponse();
                }
            }
            return new APIResponse();
        }
    }

    /**
     * 非系统用户登录
     * @param sysUser
     * @return
     */
    @Override
    public APIResponse login(SYSUser sysUser) {
        /*SYSUser sysUser1 = sysUserMapper.selectOne(
                new QueryWrapper<SYSUser>().eq("username", sysUser.getUsername()));
        if (sysUser1!= null) {
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            boolean checkPass = bCryptPasswordEncoder.matches(sysUser.getPassword(), sysUser1.getPassword());
            if (checkPass) {
                //登录判断成功时 创建或者修改token
                String token = sysTokenService.createToken(sysUser1.getId());
                SYSToken sysToken = sysTokenMapper.selectOne(
                        new QueryWrapper<SYSToken>().eq("user_id", sysUser1.getId()));
                if (sysToken != null) {   //若此用户有过登录历史，则为其更新token
                    sysToken.setToken(token);
                    sysTokenMapper.updateById(sysToken);
                } else {   //若无登录历史，则为其创建token
                    SYSToken sysToken1 = new SYSToken();
                    sysToken1.setUserId(sysUser1.getId());
                    sysToken1.setToken(token);
                    sysTokenMapper.insert(sysToken1);
                }

                Map<String, Object> data = new HashMap<>();
                sysUser1.setPassword("");
                data.put("dataInfo", sysUser1);
                data.put("token", token);
                return new APIResponse(data);
            } else {
                return new APIResponse(ConstConfig.RE_USERNAME_USERPWD_ERROR_CODE, ConstConfig.RE_USERNAME_USERPWD_ERROR_MESSAGE);
            }
        } else {
            return new APIResponse(ConstConfig.RE_USERNAME_ERROR_CODE, ConstConfig.RE_USERNAME_ERRORMESSAGE);
        }*/
        return new APIResponse();
    }

    @Override
    @Transactional
    public APIResponse delete(SYSUser sysUser) {
        SYSUser sysUser1 = sysUserMapper.selectById(sysUser.getId());
        if (sysUser1 == null) {
            return new APIResponse(ConstConfig.RE_NO_EXIST_ERROR_CODE, ConstConfig.RE_NO_EXIST_ERROR_MESSAGE);
        }
        sysUser1.setDeleteFlag(ConstConfig.DELETE_FLAG_ONE);
        int res = sysUserMapper.updateById(sysUser1);
        if (res <= 0) {
            throw new ExceptionDto(ConstConfig.RE_ERROR_CODE, ConstConfig.RE_ERROR_MESSAGE);
        }
        List<SYSUserRole> list = sysUserRoleMapper.selectList(new QueryWrapper<SYSUserRole>()
                .eq("delete_flag", ConstConfig.DELETE_FLAG_ZONE)
                .eq("user_id", sysUser.getId()));
        if (list == null) {
            throw new ExceptionDto(ConstConfig.RE_ERROR_CODE, ConstConfig.RE_ERROR_MESSAGE);
        }
        List<SYSUserRole> list1 = list.stream()
                .peek(s -> s.setDeleteFlag(ConstConfig.DELETE_FLAG_ONE)).collect(Collectors.toList());
        boolean result = sysUserRoleService.updateBatchById(list1);
        if (!result) {
            throw new ExceptionDto(ConstConfig.RE_ERROR_CODE, ConstConfig.RE_ERROR_MESSAGE);
        }
        return new APIResponse();
    }

    @Override
    @Transactional
    public APIResponse update(SYSUser sysUser) {
        SYSUser sysUser1 = sysUserMapper.selectById(sysUser.getId());
        if (sysUser1 == null) {
            return new APIResponse(ConstConfig.RE_NO_EXIST_ERROR_CODE, ConstConfig.RE_NO_EXIST_ERROR_MESSAGE);
        }
        int res = sysUserMapper.updateById(sysUser);
        if (res <= 0) {
            throw new ExceptionDto(ConstConfig.RE_ERROR_CODE, ConstConfig.RE_ERROR_MESSAGE);
        }
        List<SYSUserRole> list = sysUserRoleMapper.selectList(new QueryWrapper<SYSUserRole>()
                .eq("delete_flag", ConstConfig.DELETE_FLAG_ZONE)
                .eq("user_id", sysUser.getId()));
        if (list == null) {
            throw new ExceptionDto(ConstConfig.RE_ERROR_CODE, ConstConfig.RE_ERROR_MESSAGE);
        }
        List<SYSUserRole> list1 = list.stream()
                .peek(s -> s.setDeleteFlag(ConstConfig.DELETE_FLAG_ONE)).collect(Collectors.toList());
        boolean result = sysUserRoleService.updateBatchById(list1);
        if (!result) {
            throw new ExceptionDto(ConstConfig.RE_ERROR_CODE, ConstConfig.RE_ERROR_MESSAGE);
        }
        if (sysUser.getRoleIds() == null || sysUser.getRoleIds().size() <= 0) {
            throw new ExceptionDto(ConstConfig.RE_ERROR_CODE, ConstConfig.RE_ERROR_MESSAGE);
        }
        List<SYSUserRole> list2 = new ArrayList<>();
        SYSUserRole sysUserRole;
        for (int id : sysUser.getRoleIds()) {
            sysUserRole = new SYSUserRole();
            sysUserRole.setUserId(sysUser.getId());
            sysUserRole.setRoleId(id);
            list2.add(sysUserRole);
        }
        result = sysUserRoleService.saveBatch(list2);
        if (!result) {
            throw new ExceptionDto(ConstConfig.RE_ERROR_CODE, ConstConfig.RE_ERROR_MESSAGE);
        }
        return new APIResponse();
    }

    @Override
    public APIResponse updatePassWord(SYSUser sysUser) {
        SYSUser sysUser1 = sysUserMapper.selectById(sysUser.getId());
        if (sysUser1 == null) {
            throw new ExceptionDto(ConstConfig.RE_NO_EXIST_ERROR_CODE, ConstConfig.RE_NO_EXIST_ERROR_MESSAGE);
        }
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        boolean result = bCryptPasswordEncoder.matches(sysUser.getOldPassword(), sysUser1.getPassword());
        if (!result) {
            throw new ExceptionDto(ConstConfig.RE_OLD_PASSWORD_ERROR_CODE, ConstConfig.RE_OLD_PASSWORD_ERROR_MESSAGE);
        }
        sysUser1.setPassword(bCryptPasswordEncoder.encode(sysUser.getPassword()));
        int res = sysUserMapper.updateById(sysUser1);
        if (res <= 0) {
            throw new ExceptionDto(ConstConfig.RE_ERROR_CODE, ConstConfig.RE_ERROR_MESSAGE);
        }
        return new APIResponse();
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        SYSUser sysUser = sysUserMapper.selectOne(
                new QueryWrapper<SYSUser>().eq("username", userName));
        if (sysUser != null) {
            List<SYSRole> list = sysUserMapper.userRoleList(sysUser.getId());
            sysUser.setList(list);
        } else {
            throw new UsernameNotFoundException("用户名不存在！");
        }
        return sysUser;
    }

    /**
     * 系统-用户-新增：密码加密、解密测试
     * @param args
     */
    /*public static void main(String[] args) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String rawPass = "123456";
        String password = bCryptPasswordEncoder.encode(rawPass);
        System.out.println("### " + password + " ###");
        boolean result = bCryptPasswordEncoder.matches(rawPass, password);
        System.out.println(result);
    }*/

}
