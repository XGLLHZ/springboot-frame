package org.huangzi.main.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.huangzi.main.common.utils.APIResponse;
import org.huangzi.main.common.utils.ConstConfig;
import org.huangzi.main.web.mapper.CommentMapper;
import org.huangzi.main.web.entity.BlogEntity;
import org.huangzi.main.web.entity.CommentEntity;
import org.huangzi.main.web.mapper.BlogMapper;
import org.huangzi.main.web.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: XGLLHZ
 * @date: 2020/3/15 下午7:25
 * @description: 评论事务层实现类
 */
@Primary
@Service("CommentServiceImpl")
public class CommentServiceImpl extends ServiceImpl<CommentMapper, CommentEntity> implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private BlogMapper blogMapper;

    @Override
    public APIResponse getList(CommentEntity commentEntity) {
        List<CommentEntity> list = commentMapper.getList(commentEntity);
        List<CommentEntity> list1 = new ArrayList<>();
        Map<String, Object> map = new HashMap<>(1);
        if (list == null) {
            return new APIResponse(ConstConfig.RE_ERROR_CODE, ConstConfig.RE_ERROR_MESSAGE);
        }
        for (CommentEntity commentEntity1 : getRootNode(list)) {
            commentEntity1 = buildTree(list, commentEntity1);
            list1.add(commentEntity1);
        }
        map.put(ConstConfig.DATA_LIST, list1);
        return new APIResponse(map);
    }

    @Override
    public APIResponse insertComment(CommentEntity commentEntity) {
        BlogEntity blogEntity = blogMapper.selectById(commentEntity.getBlogId());
        if (blogEntity == null) {
            return new APIResponse(ConstConfig.RE_NO_EXIST_ERROR_CODE, ConstConfig.RE_NO_EXIST_ERROR_MESSAGE);
        }
        if (commentEntity.getParentId() != null) {
            CommentEntity commentEntity1 = commentMapper.selectById(commentEntity.getParentId());
            if (commentEntity1 == null) {
                return new APIResponse(ConstConfig.RE_NO_EXIST_ERROR_CODE, ConstConfig.RE_NO_EXIST_ERROR_MESSAGE);
            }
        }
        int res = commentMapper.insert(commentEntity);
        if (res <= 0 ) {
            return new APIResponse(ConstConfig.RE_ERROR_CODE, ConstConfig.RE_ERROR_MESSAGE);
        }
        //博客评论数 + 1
        int number = blogEntity.getCommentNumber();
        blogEntity.setCommentNumber(number + 1);
        res = blogMapper.updateById(blogEntity);
        if (res <= 0 ) {
            return new APIResponse(ConstConfig.RE_ERROR_CODE, ConstConfig.RE_ERROR_MESSAGE);
        }
        return new APIResponse();
    }

    @Override
    public APIResponse deleteComment(CommentEntity commentEntity) {
        BlogEntity blogEntity = blogMapper.selectById(commentEntity.getBlogId());
        CommentEntity commentEntity1 = commentMapper.selectById(commentEntity.getId());
        if (blogEntity == null || commentEntity1 == null) {
            return new APIResponse(ConstConfig.RE_NO_EXIST_ERROR_CODE, ConstConfig.RE_NO_EXIST_ERROR_MESSAGE);
        }
        List<CommentEntity> list = commentMapper.selectList(new QueryWrapper<CommentEntity>()
                .eq("delete_flag", ConstConfig.DELETE_FLAG_ZONE)
                .eq("parent_id", commentEntity.getId()));
        if (list == null || list.size() <= 0) {
            commentEntity1.setDeleteFlag(ConstConfig.DELETE_FLAG_ONE);
            //博客评论数 - 1
            int number = blogEntity.getCommentNumber();
            blogEntity.setCommentNumber(number - 1);
        } else {
            commentEntity1.setComment("该评论已被删除！");
        }
        int res = commentMapper.updateById(commentEntity1);
        if (res <= 0 ) {
            return new APIResponse(ConstConfig.RE_ERROR_CODE, ConstConfig.RE_ERROR_MESSAGE);
        }
        res = blogMapper.updateById(blogEntity);
        if (res <= 0 ) {
            return new APIResponse(ConstConfig.RE_ERROR_CODE, ConstConfig.RE_ERROR_MESSAGE);
        }
        return new APIResponse();
    }

    /**
     * 获取根节点
     * @param list
     * @return
     */
    private List<CommentEntity> getRootNode(List<CommentEntity> list) {
        List<CommentEntity> list1 = new ArrayList<>();
        if (list != null && list.size() > 0) {
            for (CommentEntity commentEntity : list) {
                if (ConstConfig.TYPE_ZERO.equals(commentEntity.getParentId())) {
                    list1.add(commentEntity);
                }
            }
        }
        return list1;
    }

    /**
     * 构建树形结构
     * @param list
     * @param commentEntity
     * @return
     */
    private CommentEntity buildTree(List<CommentEntity> list, CommentEntity commentEntity) {
        List<CommentEntity> list1 = new ArrayList<>();
        if (list != null && list.size() > 0) {
            for (CommentEntity commentEntity1 : list) {
                if (commentEntity.getId().equals(commentEntity1.getParentId())) {
                    list1.add(buildTree(list, commentEntity1));
                }
            }
            commentEntity.setChildrenList(list1);
        }
        return commentEntity;
    }

}
