package org.huangzi.main.authority.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;
import org.huangzi.main.common.utils.BaseEntity;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author: XGLLHZ
 * @date: 2019/8/19 23:21
 * @description: 系统-角色表
 */
@Data
@Accessors(chain = true)
@TableName("sys_role")
public class SYSRole extends BaseEntity implements Serializable {

    @TableId(type = IdType.AUTO)
    private Integer id;   //角色主键

    private String roleNamey;   //英文名

    private String roleNamez;   //中文名

    private Integer deleteFlag;   //删除状态：0：未删除；1：已删除

    private Timestamp createTime;   //创建时间

    private Timestamp updateTime;   //修改时间

    @TableField(exist = false)
    private int[] permIds;   //角色对应的权限id数组

}
