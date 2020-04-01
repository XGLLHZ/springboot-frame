package org.huangzi.main.goods.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.huangzi.main.goods.entity.SKUEntity;

import java.util.List;

/**
 * @author: XGLLHZ
 * @date: 2020/3/18 下午10:49
 * @description: sku mapper 接口
 */
public interface SKUMapper extends BaseMapper<SKUEntity> {

    /**
     * 列表-分页（根据 spuId 获取）
     * @param page
     * @param skuEntity
     * @return
     */
    List<SKUEntity> getList(Page<SKUEntity> page, @Param("condition") SKUEntity skuEntity);

    /**
     * 数量
     * @param skuEntity
     * @return
     */
    Integer getTotal(@Param("condition") SKUEntity skuEntity);

}
