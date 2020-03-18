package org.huangzi.main.web.controller;

import org.huangzi.main.common.utils.APIResponse;
import org.huangzi.main.web.service.DepartmentService;
import org.huangzi.main.common.annotation.LogAnnotation;
import org.huangzi.main.web.entity.DepartmentEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: XGLLHZ
 * @date: 2020/2/2 下午4:44
 * @description: 部门前端控制器-web
 */
@RestController
@RequestMapping("/admin/department")
public class DepartmentController {

    @Autowired
    DepartmentService departmentService;

    @LogAnnotation("部门列表")
    @RequestMapping("/list")
    public APIResponse departmentList(@RequestBody DepartmentEntity departmentEntity) {
        return departmentService.departmentList(departmentEntity);
    }

    @LogAnnotation("部门详情")
    @RequestMapping("/get")
    public APIResponse getDepartment(@RequestBody DepartmentEntity departmentEntity) {
        return departmentService.getDepartment(departmentEntity);
    }

    @LogAnnotation("新增部门")
    @RequestMapping("/insert")
    public APIResponse insertDepartment(@RequestBody DepartmentEntity departmentEntity) {
        return departmentService.insertDepartment(departmentEntity);
    }

    @LogAnnotation("删除部门")
    @RequestMapping("/delete")
    public APIResponse deleteDepartment(@RequestBody DepartmentEntity departmentEntity) {
        return departmentService.departmentList(departmentEntity);
    }

    @LogAnnotation("修改部门")
    @RequestMapping("/update")
    public APIResponse updateDepartment(@RequestBody DepartmentEntity departmentEntity) {
        return departmentService.updateDepartment(departmentEntity);
    }

}
