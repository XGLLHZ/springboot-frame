package org.huangzi.main.authority.controller;

import org.huangzi.main.common.annotation.LogAnnotation;
import org.huangzi.main.common.utils.APIResponse;
import org.huangzi.main.authority.entity.SYSRole;
import org.huangzi.main.authority.service.SYSRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: XGLLHZ
 * @date: 2019/8/20 15:37
 * @description: 系统-角色前端控制器
 */
@RestController
@RequestMapping("/admin/role")
public class SYSRoleController {

    @Autowired
    SYSRoleService sysRoleService;

    /**
     * 获取数据列表
     * @param sysRole
     * @return
     */
    @LogAnnotation("角色列表")
    @RequestMapping("/list")
    public APIResponse list(@RequestBody SYSRole sysRole) {
        return sysRoleService.list(sysRole);
    }

    /**
     * 获取详情
     * @param sysRole
     * @return
     */
    @LogAnnotation("角色详情")
    @RequestMapping("/get")
    public APIResponse get(@RequestBody SYSRole sysRole) {
        return sysRoleService.get(sysRole);
    }

    /**
     * 新增
     * @param sysRole
     * @return
     */
    @LogAnnotation("新增角色")
    @RequestMapping("/insert")
    public APIResponse insert(@RequestBody SYSRole sysRole) {
        return sysRoleService.insert(sysRole);
    }

    /**
     * 删除
     * @param sysRole
     * @return
     */
    @LogAnnotation("删除角色")
    @RequestMapping("/delete")
    public APIResponse delete(@RequestBody SYSRole sysRole) {
        return sysRoleService.delete(sysRole);
    }

    /**
     * 修改
     * @param sysRole
     * @return
     */
    @LogAnnotation("修改角色")
    @RequestMapping("/update")
    public APIResponse update(@RequestBody SYSRole sysRole) {
        return sysRoleService.update(sysRole);
    }

    @LogAnnotation("全部角色列表")
    @RequestMapping("/getAll")
    public APIResponse getAllRole() {
        return sysRoleService.getAllRole();
    }

}
