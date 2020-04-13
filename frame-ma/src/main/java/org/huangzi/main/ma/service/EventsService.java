package org.huangzi.main.ma.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.huangzi.main.common.utils.APIResponse;
import org.huangzi.main.ma.entity.EventsEntity;

/**
 * @author: XGLLHZ
 * @date: 2020/4/13 上午11:43
 * @description: 事件事务层接口
 */
public interface EventsService extends IService<EventsEntity> {

    /**
     * 列表-分页
     * @param eventsEntity
     * @return
     */
    APIResponse getList(EventsEntity eventsEntity);

    /**
     * 按 年-月 查找
     * @param eventsEntity eventTime: 2020-3
     * @return
     */
    APIResponse getSearch(EventsEntity eventsEntity);

    /**
     * 新增
     * @param eventsEntity
     * @return
     */
    APIResponse insertEvent(EventsEntity eventsEntity);

    /**
     * 删除
     * @param eventsEntity
     * @return
     */
    APIResponse deleteEvent(EventsEntity eventsEntity);

}
