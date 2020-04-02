package org.huangzi.main.web.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;
import org.huangzi.main.common.utils.BaseEntity;

import java.io.Serializable;

/**
 * @author: XGLLHZ
 * @date: 2020/3/11 下午10:02
 * @description: 富文本实体类
 */
@Data
@Accessors(chain = true)
@TableName("blog_content")
public class ContentEntity extends BaseEntity implements Serializable {

    private Integer contentType;   //类型：0：全部；1：文章

    private Integer objectId;   //类型id

    private String content;   //富文本

}
