package org.huangzi.main.authority.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;
import org.huangzi.main.common.utils.BaseEntity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

/**
 * @author: XGLLHZ
 * @date: 2019/8/19 23:21
 * @description: 系统-角色表
 */
@Data
@ToString
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@TableName("sys_role")
public class SYSRole extends BaseEntity implements Serializable {

    private String roleNamey;   //英文名

    private String roleNamez;   //中文名

    @TableField(exist = false)
    private List<Integer> permIds;   //权限 ids

    @TableField(exist = false)
    private List<SYSPermission> list;   //权限列表

}
