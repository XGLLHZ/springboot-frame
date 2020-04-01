package org.huangzi.main.goods.controller;

import org.huangzi.main.common.annotation.LogAnnotation;
import org.huangzi.main.common.utils.APIResponse;
import org.huangzi.main.goods.entity.SKUEntity;
import org.huangzi.main.goods.service.SKUService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: XGLLHZ
 * @date: 2020/4/1 下午10:43
 * @description: sku 前端控制器
 */
@RestController
@RequestMapping("/goods/admin/sku")
public class SKUController {

    @Autowired
    private SKUService skuService;

    @LogAnnotation("sku 列表/根据 spuId 获取 sku 列表")
    @RequestMapping("/list")
    public APIResponse getList(@RequestBody SKUEntity skuEntity) {
        return skuService.getList(skuEntity);
    }

    @LogAnnotation("sku 详情")
    @RequestMapping("/get")
    public APIResponse getSKU(@RequestBody SKUEntity skuEntity) {
        return skuService.getSKU(skuEntity);
    }

    @LogAnnotation("新增 sku")
    @RequestMapping("/insert")
    public APIResponse insertSKU(@RequestBody SKUEntity skuEntity) {
        return skuService.insertSKU(skuEntity);
    }

    @LogAnnotation("删除 sku")
    @RequestMapping("/delete")
    public APIResponse deleteSKU(@RequestBody SKUEntity skuEntity) {
        return skuService.deleteSKU(skuEntity);
    }

    @LogAnnotation("修改 sku")
    @RequestMapping("/update")
    public APIResponse updateSKU(@RequestBody SKUEntity skuEntity) {
        return skuService.updateSKU(skuEntity);
    }

}
