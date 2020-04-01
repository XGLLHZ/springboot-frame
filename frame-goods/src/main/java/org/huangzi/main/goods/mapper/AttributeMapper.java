package org.huangzi.main.goods.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.huangzi.main.goods.entity.AttributeEntity;

import java.util.List;

/**
 * @author: XGLLHZ
 * @date: 2020/3/18 下午10:45
 * @description: 属性 mapper 接口
 */
public interface AttributeMapper extends BaseMapper<AttributeEntity> {

    /**
     * 列表-分页
     * @param page
     * @param attributeEntity
     * @return
     */
    List<AttributeEntity> getList(Page<AttributeEntity> page, @Param("condition") AttributeEntity attributeEntity);

    /**
     * 数量
     * @param attributeEntity
     * @return
     */
    Integer getTotal(@Param("condition") AttributeEntity attributeEntity);

}
