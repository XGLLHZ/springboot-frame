package org.huangzi.main.goods.controller;

import org.huangzi.main.common.annotation.LogAnnotation;
import org.huangzi.main.common.utils.APIResponse;
import org.huangzi.main.goods.entity.BrandEntity;
import org.huangzi.main.goods.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: XGLLHZ
 * @date: 2020/3/31 下午5:04
 * @description: 品牌前端控制器
 */
@RestController
@RequestMapping("/goods/admin/brand")
public class BrandController {

    @Autowired
    private BrandService brandService;

    @LogAnnotation("品牌列表")
    @RequestMapping("/list")
    public APIResponse getList(@RequestBody BrandEntity brandEntity) {
        return brandService.getList(brandEntity);
    }

    @LogAnnotation("品牌详情")
    @RequestMapping("/get")
    public APIResponse getBrand(@RequestBody BrandEntity brandEntity) {
        return brandService.getBrand(brandEntity);
    }

    @LogAnnotation("新增品牌")
    @RequestMapping("/insert")
    public APIResponse insertBrand(@RequestBody BrandEntity brandEntity) {
        return brandService.insertBrand(brandEntity);
    }

    @LogAnnotation("删除品牌")
    @RequestMapping("/delete")
    public APIResponse deleteBrand(@RequestBody BrandEntity brandEntity) {
        return brandService.deleteBrand(brandEntity);
    }

    @LogAnnotation("修改品牌")
    @RequestMapping("/update")
    public APIResponse updateBrand(@RequestBody BrandEntity brandEntity) {
        return brandService.updateBrand(brandEntity);
    }

    @LogAnnotation("全部品牌列表")
    @RequestMapping("/getall")
    public APIResponse getAllBrand(@RequestBody BrandEntity brandEntity) {
        return brandService.getAllBrand(brandEntity);
    }

}
