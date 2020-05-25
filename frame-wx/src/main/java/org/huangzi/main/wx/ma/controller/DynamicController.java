package org.huangzi.main.wx.ma.controller;

import org.huangzi.main.common.annotation.LogAnnotation;
import org.huangzi.main.common.utils.APIResponse;
import org.huangzi.main.wx.ma.entity.DynamicEntity;
import org.huangzi.main.wx.ma.service.DynamicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: XGLLHZ
 * @date: 2020/4/13 下午7:25
 * @description: 动态前端控制器
 */
@RestController
@RequestMapping("/ma/dynamic")
public class DynamicController {

    @Autowired
    private DynamicService dynamicService;

    @LogAnnotation("动态列表-分页")
    @RequestMapping("/list")
    public APIResponse getList(@RequestBody DynamicEntity dynamicEntity) {
        return dynamicService.getList(dynamicEntity);
    }

    @LogAnnotation("新增动态")
    @RequestMapping("/insert")
    public APIResponse insertDynamic(@RequestBody DynamicEntity dynamicEntity) {
        return dynamicService.insertDynamic(dynamicEntity);
    }

    @LogAnnotation("删除动态")
    @RequestMapping("/delete")
    public APIResponse deleteDynamic(@RequestBody DynamicEntity dynamicEntity) {
        return dynamicService.deleteDynamic(dynamicEntity);
    }

    @LogAnnotation("修改动态")
    @RequestMapping("/update")
    public APIResponse updateDynamic(@RequestBody DynamicEntity dynamicEntity) {
        return dynamicService.updateDynamic(dynamicEntity);
    }

}
