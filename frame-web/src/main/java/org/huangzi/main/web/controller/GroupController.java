package org.huangzi.main.web.controller;

import org.huangzi.main.common.annotation.LogAnnotation;
import org.huangzi.main.common.utils.APIResponse;
import org.huangzi.main.web.entity.GroupEntity;
import org.huangzi.main.web.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: XGLLHZ
 * @date: 2020/5/19 下午3:26
 * @description: 用户群组前端控制器
 */
@RestController
@RequestMapping("/web/admin/group")
public class GroupController {

    @Autowired
    private GroupService groupService;

    @LogAnnotation("全部群组")
    @RequestMapping("/list")
    public APIResponse getList(@RequestBody GroupEntity groupEntity) {
        return groupService.getList(groupEntity);
    }

    @LogAnnotation("群组详情")
    @RequestMapping("/get")
    public APIResponse getGroup(@RequestBody GroupEntity groupEntity) {
        return groupService.getGroup(groupEntity);
    }

    @LogAnnotation("我的群组列表")
    @RequestMapping("/myGroup")
    public APIResponse getMyGroup(@RequestBody GroupEntity groupEntity) {
        return groupService.getMyGroup(groupEntity);
    }

}
