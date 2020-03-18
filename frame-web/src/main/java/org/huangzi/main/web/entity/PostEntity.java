package org.huangzi.main.web.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;
import org.huangzi.main.common.utils.BaseEntity;

import java.io.Serializable;

/**
 * @author: XGLLHZ
 * @date: 2020/1/20 下午5:44
 * @description: 岗位实体类
 */
@Data
@Accessors(chain = true)
@TableName("bus_post")
public class PostEntity extends BaseEntity implements Serializable {

    private Integer parentId;   //上级部门 id

    private String postName;   //岗位名称

    private String postCode;   //岗位代码

}
