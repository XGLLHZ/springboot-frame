package org.huangzi.main.web.controller;

import org.huangzi.main.common.utils.APIResponse;
import org.huangzi.main.web.entity.PostEntity;
import org.huangzi.main.web.service.PostService;
import org.huangzi.main.common.annotation.LogAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: XGLLHZ
 * @date: 2020/2/2 下午4:45
 * @description: 岗位前端控制器-web
 */
@RestController
@RequestMapping("/admin/post")
public class PostController {

    @Autowired
    PostService postService;

    @LogAnnotation("岗位列表")
    @RequestMapping("/list")
    public APIResponse postList(@RequestBody PostEntity postEntity) {
        return postService.postList(postEntity);
    }

    @LogAnnotation("岗位详情")
    @RequestMapping("/get")
    public APIResponse getPost(@RequestBody PostEntity postEntity) {
        return postService.getPost(postEntity);
    }

    @LogAnnotation("新增岗位")
    @RequestMapping("/insert")
    public APIResponse insertPost(@RequestBody PostEntity postEntity) {
        return postService.insertPost(postEntity);
    }

    @LogAnnotation("删除岗位")
    @RequestMapping("/delete")
    public APIResponse deletePost(@RequestBody PostEntity postEntity) {
        return postService.deletePost(postEntity);
    }

    @LogAnnotation("修改岗位")
    @RequestMapping("/update")
    public APIResponse updatePost(@RequestBody PostEntity postEntity) {
        return postService.updatePost(postEntity);
    }

}
