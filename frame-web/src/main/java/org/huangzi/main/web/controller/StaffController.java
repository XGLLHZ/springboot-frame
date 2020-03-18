package org.huangzi.main.web.controller;

import org.huangzi.main.common.utils.APIResponse;
import org.huangzi.main.web.entity.StaffEntity;
import org.huangzi.main.web.service.StaffService;
import org.huangzi.main.common.annotation.LogAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: XGLLHZ
 * @date: 2020/2/2 下午4:45
 * @description: 员工前端控制器-web
 */
@RestController
@RequestMapping("/admin/staff")
public class StaffController {

    @Autowired
    StaffService staffService;

    @LogAnnotation("员工列表")
    @RequestMapping("/list")
    public APIResponse staffList(@RequestBody StaffEntity staffEntity) {
        return staffService.staffList(staffEntity);
    }

    @LogAnnotation("员工详情")
    @RequestMapping("/get")
    public APIResponse getStaff(@RequestBody StaffEntity staffEntity) {
        return staffService.getStaff(staffEntity);
    }

    @LogAnnotation("新增员工")
    @RequestMapping("/insert")
    public APIResponse insertStaff(@RequestBody StaffEntity staffEntity) {
        return staffService.insertStaff(staffEntity);
    }

    @LogAnnotation("删除员工")
    @RequestMapping("/delete")
    public APIResponse deleteStaff(@RequestBody StaffEntity staffEntity) {
        return staffService.deleteStaff(staffEntity);
    }

    @LogAnnotation("修改员工")
    @RequestMapping("/update")
    public APIResponse updateStaff(@RequestBody StaffEntity staffEntity) {
        return staffService.updateStaff(staffEntity);
    }

}
