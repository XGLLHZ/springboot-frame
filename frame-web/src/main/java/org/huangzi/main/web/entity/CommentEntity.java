package org.huangzi.main.web.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;
import org.huangzi.main.common.utils.BaseEntity;

import java.io.Serializable;
import java.util.List;

/**
 * @author: XGLLHZ
 * @date: 2020/3/15 下午6:00
 * @description: 评论实体类
 */
@Data
@Accessors(chain = true)
@TableName("blog_comment")
public class CommentEntity extends BaseEntity implements Serializable {

    private Integer blogId;   //博客 id

    private Integer parentId;   //父评论 id

    private Integer userId;   //评论者（即用户） id

    private String comment;   //评论内容

    @TableField(exist = false)
    private String userName;   //用户名

    @TableField(exist = false)
    private String userLogo;   //用户头像

    @TableField(exist = false)
    private List<CommentEntity> childrenList;   //子列表

}
