package org.huangzi.main.ma.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.huangzi.main.ma.entity.EventsEntity;

import java.util.List;

/**
 * @author: XGLLHZ
 * @date: 2020/4/13 上午11:32
 * @description: 事件 mapper 接口
 */
public interface EventsMapper extends BaseMapper<EventsEntity> {

    /**
     * 列表-分页
     * @param page
     * @param eventsEntity
     * @return
     */
    List<EventsEntity> getList(Page<EventsEntity> page, @Param("condition") EventsEntity eventsEntity);

    /**
     * 数量
     * @param eventsEntity
     * @return
     */
    Integer getTotal(@Param("condition") EventsEntity eventsEntity);

}
