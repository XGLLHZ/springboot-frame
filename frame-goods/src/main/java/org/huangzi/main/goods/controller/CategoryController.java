package org.huangzi.main.goods.controller;

import org.huangzi.main.common.annotation.LogAnnotation;
import org.huangzi.main.common.utils.APIResponse;
import org.huangzi.main.goods.entity.CategoryEntity;
import org.huangzi.main.goods.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: XGLLHZ
 * @date: 2020/3/31 下午10:41
 * @description: 分类前端控制器
 */
@RestController
@RequestMapping("/goods/admin/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @LogAnnotation("分类列表-树形结构")
    @RequestMapping("/list")
    public APIResponse getAllCategory(@RequestBody CategoryEntity categoryEntity) {
        return categoryService.getAllCategory(categoryEntity);
    }

    @LogAnnotation("分类详情")
    @RequestMapping("/get")
    public APIResponse getCategory(@RequestBody CategoryEntity categoryEntity) {
        return categoryService.getCategory(categoryEntity);
    }

    @LogAnnotation("新增分类")
    @RequestMapping("/insert")
    public APIResponse insertCategory(@RequestBody CategoryEntity categoryEntity) {
        return categoryService.insertCategory(categoryEntity);
    }

    @LogAnnotation("删除分类")
    @RequestMapping("/delete")
    public APIResponse deleteCategory(@RequestBody CategoryEntity categoryEntity) {
        return categoryService.deleteCategory(categoryEntity);
    }

    @LogAnnotation("修改分类")
    @RequestMapping("/update")
    public APIResponse updateCategory(@RequestBody CategoryEntity categoryEntity) {
        return categoryService.updateCategory(categoryEntity);
    }

}
