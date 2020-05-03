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
 * @date: 2019/8/19 23:27
 * @description: 系统-权限表
 */
@Data
@ToString
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@TableName("sys_permission")
public class SYSPermission extends BaseEntity implements Serializable {

    private Integer parentId;   //父菜单id

    private String permName;   //权限名称（菜单名称）

    private String permUrl;   //权限url

    private String routerComponent;   //路由组件

    private String routerPath;   //路由 path

    private String routerIcon;   //路由 icon

    private Integer routerHidden;   //路由 hidden 值：0：显示；1：隐藏

    private Integer permType;   //权限类型：1：目录；2：菜单；3：操作

    private Integer permSort;   //权限排序

    @TableField(exist = false)
    private List<SYSRole> roles;   //角色英文名

    @TableField(exist = false)
    private List<SYSPermission> childrenList;   //子菜单

    @TableField(exist = false)
    private String parentName;   //父菜单名称

    @TableField(exist = false)
    private Integer userId;   //用户 id

}
