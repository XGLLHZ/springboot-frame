package org.huangzi.main.authority.controller;

import org.huangzi.main.common.annotation.LogAnnotation;
import org.huangzi.main.common.utils.APIResponse;
import org.huangzi.main.common.utils.ConstConfig;
import org.huangzi.main.authority.entity.SYSUser;
import org.huangzi.main.authority.service.SYSUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: XGLLHZ
 * @date: 2019/8/20 12:16
 * @description: 系统-用户前端控制器
 */
@RestController
@RequestMapping("/admin/user")
public class SYSUserController {

    @Autowired
    SYSUserService sysUserService;

    /**
     * 获取数据列表
     * @param sysUser
     * @return
     */
    @LogAnnotation("用户列表")
    @RequestMapping("/list")
    public APIResponse list(@RequestBody SYSUser sysUser) {
        return sysUserService.list(sysUser);
    }

    /**
     * 详情
     * @param sysUser
     * @return
     */
    @LogAnnotation("用户详情")
    @RequestMapping("/get")
    public APIResponse get(@RequestBody SYSUser sysUser) {
        return sysUserService.get(sysUser);
    }

    /**
     * 用户-新增-注册
     * @param sysUser
     * @return
     */
    @LogAnnotation("新增用户-注册")
    @RequestMapping("/register")
    public APIResponse insert(@RequestBody SYSUser sysUser) {
        return sysUserService.insert(sysUser);
    }

    /**
     * 用户-登录
     * @param sysUser
     * @return
     */
    @RequestMapping("/login")
    public APIResponse login(@RequestBody SYSUser sysUser) {
        return sysUserService.login(sysUser);
    }

    /**
     * spring security 返回登录提示
     * @return
     */
    @LogAnnotation("用户登录")
    @RequestMapping("/login_code")
    public APIResponse loginCode() {
        return new APIResponse(ConstConfig.RE_PLEASE_LOGIN_FIRST_CODE, ConstConfig.RE_PLEASE_LOGIN_FIRST_MESSAGE);
    }

    /**
     * 删除
     * @param sysUser
     * @return
     */
    @LogAnnotation("删除用户")
    @RequestMapping("/delete")
    public APIResponse delete(@RequestBody SYSUser sysUser) {
        return sysUserService.delete(sysUser);
    }

    /**
     * 修改
     * @param sysUser
     * @return
     */
    @LogAnnotation("修改用户")
    @RequestMapping("/update")
    public APIResponse update(@RequestBody SYSUser sysUser) {
        return sysUserService.update(sysUser);
    }

}
