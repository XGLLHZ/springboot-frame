package org.huangzi.main.web.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.huangzi.main.common.utils.APIResponse;
import org.huangzi.main.web.entity.CommentEntity;

/**
 * @author: XGLLHZ
 * @date: 2020/3/15 下午7:18
 * @description: 评论事务层接口
 */
public interface CommentService extends IService<CommentEntity> {

    /**
     * 获取评论列表（树形结构）
     * @param commentEntity
     * @return
     */
    APIResponse getList(CommentEntity commentEntity);

    /**
     * 新增
     * @param commentEntity
     * @return
     */
    APIResponse insertComment(CommentEntity commentEntity);

    /**
     * 删除
     * @param commentEntity
     * @return
     */
    APIResponse deleteComment(CommentEntity commentEntity);

}
