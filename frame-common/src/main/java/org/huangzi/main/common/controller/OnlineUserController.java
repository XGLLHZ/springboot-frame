package org.huangzi.main.common.controller;

import org.huangzi.main.common.annotation.LogAnnotation;
import org.huangzi.main.common.utils.APIResponse;
import org.huangzi.main.common.entity.OnlineUserEntity;
import org.huangzi.main.common.service.OnlineUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: XGLLHZ
 * @date: 2020/2/4 下午6:20
 * @description: 在线用户前端控制器-web
 */
@RestController
@RequestMapping("/admin/online")
public class OnlineUserController {

    @Autowired
    OnlineUserService onlineUserService;

    /**
     * 获取在线用户列表
     * @return
     */
    @LogAnnotation("在线用户")
    @RequestMapping("/list")
    public APIResponse getListOnlineUser(@RequestBody OnlineUserEntity onlineUserEntity) {
        return onlineUserService.getListOnlineUser(onlineUserEntity.getUserName());
    }

}
