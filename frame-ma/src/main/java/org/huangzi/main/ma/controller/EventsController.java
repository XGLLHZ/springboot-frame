package org.huangzi.main.ma.controller;

import org.huangzi.main.common.annotation.LogAnnotation;
import org.huangzi.main.common.utils.APIResponse;
import org.huangzi.main.ma.entity.EventsEntity;
import org.huangzi.main.ma.service.EventsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: XGLLHZ
 * @date: 2020/4/13 下午2:11
 * @description: 事件前端控制器
 */
@RestController
@RequestMapping("/ma/events")
public class EventsController {

    @Autowired
    private EventsService eventsService;

    @LogAnnotation("事件列表-分页")
    @RequestMapping("/list")
    public APIResponse getList(@RequestBody EventsEntity eventsEntity) {
        return eventsService.getList(eventsEntity);
    }

    @LogAnnotation("事件列表-日历")
    @RequestMapping("/search")
    public APIResponse getSearch(@RequestBody EventsEntity eventsEntity) {
        return eventsService.getSearch(eventsEntity);
    }

    @LogAnnotation("新增事件")
    @RequestMapping("/insert")
    public APIResponse insertEvent(@RequestBody EventsEntity eventsEntity) {
        return eventsService.insertEvent(eventsEntity);
    }

    @LogAnnotation("删除事件")
    @RequestMapping("/delete")
    public APIResponse deleteEvent(@RequestBody EventsEntity eventsEntity) {
        return eventsService.deleteEvent(eventsEntity);
    }

}
