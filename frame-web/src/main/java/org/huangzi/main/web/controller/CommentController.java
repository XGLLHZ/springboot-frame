package org.huangzi.main.web.controller;

import org.huangzi.main.common.utils.APIResponse;
import org.huangzi.main.web.service.CommentService;
import org.huangzi.main.common.annotation.LogAnnotation;
import org.huangzi.main.web.entity.CommentEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: XGLLHZ
 * @date: 2020/3/15 下午8:25
 * @description: 评论前端控制器
 */
@RestController
@RequestMapping("/web/admin/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @LogAnnotation("评论列表")
    @RequestMapping("/list")
    public APIResponse getList(@RequestBody CommentEntity commentEntity) {
        return commentService.getList(commentEntity);
    }

    @LogAnnotation("新增评论")
    @RequestMapping("/insert")
    public APIResponse insertComment(@RequestBody CommentEntity commentEntity) {
        return commentService.insertComment(commentEntity);
    }

    @LogAnnotation("删除评论")
    @RequestMapping("/delete")
    public APIResponse deleteComment(@RequestBody CommentEntity commentEntity) {
        return commentService.deleteComment(commentEntity);
    }

}
