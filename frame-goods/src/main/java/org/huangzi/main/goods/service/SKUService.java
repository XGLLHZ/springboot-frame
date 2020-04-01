package org.huangzi.main.goods.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.huangzi.main.common.utils.APIResponse;
import org.huangzi.main.goods.entity.SKUEntity;

/**
 * @author: XGLLHZ
 * @date: 2020/4/1 下午5:12
 * @description: sku 事务层接口
 */
public interface SKUService extends IService<SKUEntity> {

    /**
     * sku 列表-分页（根据 spuId 获取）
     * @param skuEntity
     * @return
     */
    APIResponse getList(SKUEntity skuEntity);

    /**
     * 详情
     * @param skuEntity
     * @return
     */
    APIResponse getSKU(SKUEntity skuEntity);

    /**
     * 新增
     * @param skuEntity
     * @return
     */
    APIResponse insertSKU(SKUEntity skuEntity);

    /**
     * 删除
     * @param skuEntity
     * @return
     */
    APIResponse deleteSKU(SKUEntity skuEntity);

    /**
     * 修改
     * @param skuEntity
     * @return
     */
    APIResponse updateSKU(SKUEntity skuEntity);

}
