package org.huangzi.main.common.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.huangzi.main.common.entity.LogEntity;

import java.util.List;

/**
 * @author: XGLLHZ
 * @date: 2020/2/6 下午7:06
 * @description: 日志 mapper 接口
 */
public interface LogMapper extends BaseMapper<LogEntity> {

    /**
     * 获取日志列表-分页
     * @param page
     * @param logEntity
     * @return
     */
    List<LogEntity> list(Page<LogEntity> page, @Param("condition") LogEntity logEntity);

    /**
     * 获取数据总数
     * @param logEntity
     * @return
     */
    Integer total(@Param("condition") LogEntity logEntity);

}
