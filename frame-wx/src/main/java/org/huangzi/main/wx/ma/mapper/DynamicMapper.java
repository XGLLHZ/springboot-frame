package org.huangzi.main.wx.ma.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.huangzi.main.wx.ma.entity.DynamicEntity;

import java.util.List;

/**
 * @author: XGLLHZ
 * @date: 2020/4/13 下午4:28
 * @description: 动态 mapper 接口
 */
public interface DynamicMapper extends BaseMapper<DynamicEntity> {

    /**
     * 列表-分页
     * @param page
     * @param dynamicEntity
     * @return
     */
    List<DynamicEntity> getList(Page<DynamicEntity> page, @Param("condition") DynamicEntity dynamicEntity);

    /**
     * 数量
     * @param dynamicEntity
     * @return
     */
    Integer getTotal(@Param("condition") DynamicEntity dynamicEntity);

}
