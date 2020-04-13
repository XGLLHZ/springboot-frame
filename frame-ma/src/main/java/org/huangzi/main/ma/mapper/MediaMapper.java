package org.huangzi.main.ma.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.huangzi.main.ma.entity.MediaEntity;

import java.util.List;

/**
 * @author: XGLLHZ
 * @date: 2020/4/13 下午4:45
 * @description: 媒体 mapper 接口
 */
public interface MediaMapper extends BaseMapper<MediaEntity> {

    /**
     * 列表-分页
     * @param page
     * @param mediaEntity
     * @return
     */
    List<MediaEntity> getList(Page<MediaEntity> page, @Param("condition") MediaEntity mediaEntity);

    /**
     * 数量
     * @param mediaEntity
     * @return
     */
    Integer getTotal(@Param("condition") MediaEntity mediaEntity);

}
