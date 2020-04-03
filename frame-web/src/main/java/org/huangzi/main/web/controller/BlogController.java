package org.huangzi.main.web.controller;

import org.huangzi.main.common.annotation.LogAnnotation;
import org.huangzi.main.common.utils.APIResponse;
import org.huangzi.main.web.entity.BlogEntity;
import org.huangzi.main.web.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * @author: XGLLHZ
 * @date: 2020/3/9 下午8:01
 * @description: 博客前端控制器
 */
@RestController
@RequestMapping("/web/admin/blog")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @LogAnnotation("博客列表")
    @RequestMapping("/list")
    public APIResponse getList(@RequestBody BlogEntity blogEntity) {
        return blogService.getList(blogEntity);
    }

    @LogAnnotation("博客详情")
    @RequestMapping("/get")
    public APIResponse getShop(@RequestBody BlogEntity blogEntity) {
        return blogService.getBlog(blogEntity);
    }

    @LogAnnotation("新增博客")
    @RequestMapping("/insert")
    public APIResponse insertBlog(@RequestBody BlogEntity blogEntity) {
        return blogService.insertBlog(blogEntity);
    }

    @LogAnnotation("删除博客")
    @RequestMapping("/delete")
    public APIResponse deleteBlog(@RequestBody BlogEntity blogEntity) {
        return blogService.deleteBlog(blogEntity);
    }

    @LogAnnotation("修改博客")
    @RequestMapping("/update")
    public APIResponse updateBlog(@RequestBody BlogEntity blogEntity) {
        return blogService.updateBlog(blogEntity);
    }

    @LogAnnotation("博客导出")
    @RequestMapping("/export")
    public APIResponse exportExcel(@RequestBody HttpServletResponse response, BlogEntity blogEntity) {
        return blogService.exportBlog(response, blogEntity);
    }

    @LogAnnotation("博客-上传图片")
    @RequestMapping("/uploadimage")
    public APIResponse uploadImage(@RequestParam MultipartFile file) {
        return blogService.uploadImage(file);
    }

    @LogAnnotation("全部博客列表")
    @RequestMapping("/getall")
    public APIResponse getAllBlog(@RequestBody BlogEntity blogEntity) {
        return blogService.getAllBlog(blogEntity);
    }

}
