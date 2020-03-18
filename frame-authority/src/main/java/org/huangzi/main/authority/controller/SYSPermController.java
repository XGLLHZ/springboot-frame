package org.huangzi.main.authority.controller;

import org.huangzi.main.common.annotation.LogAnnotation;
import org.huangzi.main.common.utils.APIResponse;
import org.huangzi.main.authority.entity.SYSPermission;
import org.huangzi.main.authority.service.SYSPermService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: XGLLHZ
 * @date: 2019/8/20 15:39
 * @description: 系统-权限前端控制器
 */
@RestController
@RequestMapping("/admin/permission")
public class SYSPermController {

    @Autowired
    SYSPermService sysPermService;

    /**
     * 获取数据列表
     * @param sysPermission
     * @return
     */
    @LogAnnotation("权限列表")
    @RequestMapping("/list")
    public APIResponse list(@RequestBody SYSPermission sysPermission) {
        return sysPermService.list(sysPermission);
    }

    /**
     * 详情
     * @param sysPermission
     * @return
     */
    @LogAnnotation("权限详情")
    @RequestMapping("/get")
    public APIResponse get(@RequestBody SYSPermission sysPermission) {
        return sysPermService.get(sysPermission);
    }

    /**
     * 新增
     * @param sysPermission
     * @return
     */
    @LogAnnotation("新增权限")
    @RequestMapping("/insert")
    public APIResponse insert(@RequestBody SYSPermission sysPermission) {
        return sysPermService.insert(sysPermission);
    }

    /**
     * 删除
     * @param sysPermission
     * @return
     */
    @LogAnnotation("删除权限")
    @RequestMapping("/delete")
    public APIResponse delete(@RequestBody SYSPermission sysPermission) {
        return sysPermService.delete(sysPermission);
    }

    /**
     * 修改
     * @param sysPermission
     * @return
     */
    @LogAnnotation("修改权限")
    @RequestMapping("/update")
    public APIResponse update(@RequestBody SYSPermission sysPermission) {
        return sysPermService.update(sysPermission);
    }

    /**
     * 构建菜单树
     * @param sysPermission
     * @return
     */
    @LogAnnotation("构建菜单树")
    @RequestMapping("/buildMenu")
    public APIResponse buildMenu(@RequestBody SYSPermission sysPermission) {
        return sysPermService.buildMenu(sysPermission);
    }

    /**
     * 测试构建树形菜单-不分级
     * @return
     */
    @RequestMapping("/getall")
    public APIResponse getAllPerm() {
        return sysPermService.getAllPerm();
    }

}
