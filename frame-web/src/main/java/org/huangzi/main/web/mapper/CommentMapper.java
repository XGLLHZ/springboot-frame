package org.huangzi.main.web.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.huangzi.main.web.entity.CommentEntity;

import java.util.List;

/**
 * @author: XGLLHZ
 * @date: 2020/3/15 下午6:07
 * @description: 评论表 mapper 接口
 */
public interface CommentMapper extends BaseMapper<CommentEntity> {

    /**
     * 获取评论列表
     * @param commentEntity
     * @return
     */
    List<CommentEntity> getList(@Param("condition") CommentEntity commentEntity);

}
