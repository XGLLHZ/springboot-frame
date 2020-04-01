package org.huangzi.main.goods.controller;

import org.huangzi.main.common.annotation.LogAnnotation;
import org.huangzi.main.common.utils.APIResponse;
import org.huangzi.main.goods.entity.AttributeEntity;
import org.huangzi.main.goods.service.AttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: XGLLHZ
 * @date: 2020/4/1 上午10:11
 * @description: 类别属性前端控制器
 */
@RestController
@RequestMapping("/goods/admin/attribute")
public class AttributeController {

    @Autowired
    private AttributeService attributeService;

    @LogAnnotation("属性列表")
    @RequestMapping("/list")
    public APIResponse getList(@RequestBody AttributeEntity attributeEntity) {
        return attributeService.getList(attributeEntity);
    }

    @LogAnnotation("属性详情/根据属性获取属性值")
    @RequestMapping("/get")
    public APIResponse getAttribute(@RequestBody AttributeEntity attributeEntity) {
        return attributeService.getAttribute(attributeEntity);
    }

    @LogAnnotation("新增属性")
    @RequestMapping("/insert")
    public APIResponse insertAttribute(@RequestBody AttributeEntity attributeEntity) {
        return attributeService.insertAttribute(attributeEntity);
    }

    @LogAnnotation("删除属性")
    @RequestMapping("/delete")
    public APIResponse deleteAttribute(@RequestBody AttributeEntity attributeEntity) {
        return attributeService.deleteAttribute(attributeEntity);
    }

    @LogAnnotation("修改属性")
    @RequestMapping("/update")
    public APIResponse updateAttribute(@RequestBody AttributeEntity attributeEntity) {
        return attributeService.updateAttribute(attributeEntity);
    }

    @LogAnnotation("根据类别查找属性")
    @RequestMapping("/getbycategoryid")
    public APIResponse getAttributeByCategoryId(@RequestBody AttributeEntity attributeEntity) {
        return attributeService.getAttributeByCategoryId(attributeEntity);
    }

}
