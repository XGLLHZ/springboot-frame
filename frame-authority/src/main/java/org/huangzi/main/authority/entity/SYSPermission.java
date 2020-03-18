package org.huangzi.main.authority.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;
import org.huangzi.main.common.utils.BaseEntityUtil;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

/**
 * @author: XGLLHZ
 * @date: 2019/8/19 23:27
 * @description: 系统-权限表
 */
@Data
@Accessors(chain = true)
@TableName("sys_permission")
public class SYSPermission extends BaseEntityUtil implements Serializable {

    @TableId(type = IdType.AUTO)
    private Integer id;   //权限主键

    private Integer parentId;   //父菜单id

    private String permName;   //权限名称（菜单名称）

    private String permUrl;   //权限url

    private String routerComponent;   //路由组件

    private String routerPath;   //路由 path

    private String routerIcon;   //路由 icon

    private Integer routerHidden;   //路由 hidden 值：0：显示；1：隐藏

    private Integer permType;   //权限类型：1：目录；2：菜单；3：操作

    private Integer permSort;   //权限排序

    private Integer deleteFlag;   //删除状态：0：未删除；1：已删除

    private Timestamp createTime;   //创建时间

    private Timestamp updateTime;   //修改时间

    @TableField(exist = false)
    private List<SYSRole> roles;   //角色英文名

    @TableField(exist = false)
    private List<SYSPermission> childrenList;   //子菜单

}
