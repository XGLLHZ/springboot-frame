package org.huangzi.main.ma.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.huangzi.main.common.utils.APIResponse;
import org.huangzi.main.common.utils.ConstConfig;
import org.huangzi.main.ma.entity.EventsEntity;
import org.huangzi.main.ma.mapper.EventsMapper;
import org.huangzi.main.ma.service.EventsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: XGLLHZ
 * @date: 2020/4/13 上午11:47
 * @description: 事件事务层实现类
 */
@Primary
@Service("EventsServiceImpl")
public class EventsServiceImpl extends ServiceImpl<EventsMapper, EventsEntity> implements EventsService {

    @Autowired
    private EventsMapper eventsMapper;

    @Override
    public APIResponse getList(EventsEntity eventsEntity) {
        Page<EventsEntity> page = new Page<>(eventsEntity.getCurrentPage(), eventsEntity.getPageSize());
        List<EventsEntity> list = eventsMapper.getList(page, eventsEntity);
        Integer total = eventsMapper.getTotal(eventsEntity);
        Map<String, Object> map = new HashMap<>(2);
        if (list == null || total == null) {
            map.put(ConstConfig.DATA_LIST, new ArrayList<>());
            map.put(ConstConfig.TOTAL, 0);
            return new APIResponse(map);
        }
        map.put(ConstConfig.DATA_LIST, list);
        map.put(ConstConfig.TOTAL, total);
        return new APIResponse(map);
    }

    @Override
    public APIResponse getSearch(EventsEntity eventsEntity) {
        List<EventsEntity> list = eventsMapper.selectList(new QueryWrapper<EventsEntity>()
                .eq("delete_flag", ConstConfig.DELETE_FLAG_ZONE)
                .eq("user_id", eventsEntity.getUserId())
                .like("event_time", eventsEntity.getEventTime()));
        Map<String, Object> map = new HashMap<>(1);
        if (list == null) {
            map.put(ConstConfig.DATA_LIST, new ArrayList<>());
            return new APIResponse(map);
        }
        map.put(ConstConfig.DATA_LIST, list);
        return new APIResponse(map);
    }

    @Override
    public APIResponse insertEvent(EventsEntity eventsEntity) {
        int res = eventsMapper.insert(eventsEntity);
        if (res <= 0) {
            return new APIResponse(ConstConfig.RE_ERROR_CODE, ConstConfig.RE_ERROR_MESSAGE);
        }
        return new APIResponse();
    }

    @Override
    public APIResponse deleteEvent(EventsEntity eventsEntity) {
        EventsEntity eventsEntity1 = eventsMapper.selectById(eventsEntity.getId());
        if (eventsEntity1 == null) {
            return new APIResponse(ConstConfig.RE_NO_EXIST_ERROR_CODE, ConstConfig.RE_NO_EXIST_ERROR_MESSAGE);
        }
        eventsEntity1.setDeleteFlag(ConstConfig.DELETE_FLAG_ONE);
        int res = eventsMapper.updateById(eventsEntity1);
        if (res <= 0) {
            return new APIResponse(ConstConfig.RE_ERROR_CODE, ConstConfig.RE_ERROR_MESSAGE);
        }
        return new APIResponse();
    }

}
