package org.huangzi.main.common.utils;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author: XGLLHZ
 * @date: 2019/8/20 11:02
 * @description: 实体基础类
 */
@Data
@ToString
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class BaseEntity implements Serializable {

    @TableId(type = IdType.AUTO)
    private Integer id;   //主键

    private Integer deleteFlag;   //删除状态：0：未删除；1：已删除

    @JsonSerialize(using = TimeSerialize.class)
    private Timestamp createTime;   //创建时间

    @JsonSerialize(using = TimeSerialize.class)
    private Timestamp updateTime;   //修改时间

    @TableField(exist = false)
    private Integer currentPage = 1;   //分页参数：当前页，默认为1

    @TableField(exist = false)
    private Integer pageSize = 10;   //分页参数：页面大小，默认为10

}
