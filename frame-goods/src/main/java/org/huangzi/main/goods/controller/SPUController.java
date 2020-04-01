package org.huangzi.main.goods.controller;

import org.huangzi.main.common.annotation.LogAnnotation;
import org.huangzi.main.common.utils.APIResponse;
import org.huangzi.main.goods.entity.SPUEntity;
import org.huangzi.main.goods.service.SPUService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: XGLLHZ
 * @date: 2020/4/1 下午4:29
 * @description: spu 前端控制器
 */
@RestController
@RequestMapping("/goods/admin/spu")
public class SPUController {

    @Autowired
    private SPUService spuService;

    @LogAnnotation("SPU 列表")
    @RequestMapping("/list")
    public APIResponse getList(@RequestBody SPUEntity spuEntity) {
        return spuService.getList(spuEntity);
    }

    @LogAnnotation("SPU 详情")
    @RequestMapping("/get")
    public APIResponse getSPU(@RequestBody SPUEntity spuEntity) {
        return spuService.getSPU(spuEntity);
    }

    @LogAnnotation("新增 SPU")
    @RequestMapping("/insert")
    public APIResponse insertSPU(@RequestBody SPUEntity spuEntity){
        return spuService.insertSPU(spuEntity);
    }

    @LogAnnotation("删除 SPU")
    @RequestMapping("/delete")
    public APIResponse deleteSPU(@RequestBody SPUEntity spuEntity) {
        return spuService.deleteSPU(spuEntity);
    }

    @LogAnnotation("修改 SPU")
    @RequestMapping("/update")
    public APIResponse updateSPU(@RequestBody SPUEntity spuEntity) {
        return spuService.updateSPU(spuEntity);
    }

}
