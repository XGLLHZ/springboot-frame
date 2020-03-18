package org.huangzi.main.authority.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.huangzi.main.common.utils.APIResponse;
import org.huangzi.main.authority.entity.SYSUser;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author: XGLLHZ
 * @date: 2019/8/20 11:34
 * @description: 系统-用户事务层
 */
public interface SYSUserService extends UserDetailsService, IService<SYSUser> {

    /**
     * 获取数据列表
     * @param sysUser
     * @return
     */
    APIResponse list(SYSUser sysUser);

    /**
     * 获取用户详情
     * @param sysUser
     * @return
     */
    APIResponse get(SYSUser sysUser);

    /**
     * 新增-注册
     * @param sysUser
     * @return
     */
    APIResponse insert(SYSUser sysUser);

    /**
     * 登录
     * @param sysUser
     * @return
     */
    APIResponse login(SYSUser sysUser);

    /**
     * 删除
     * @param sysUser
     * @return
     */
    APIResponse delete(SYSUser sysUser);

    /**
     * 修改
     * @param sysUser
     * @return
     */
    APIResponse update(SYSUser sysUser);

}
