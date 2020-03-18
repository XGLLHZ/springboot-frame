package org.huangzi.main.authority.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;
import org.huangzi.main.common.utils.BaseEntityUtil;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author: XGLLHZ
 * @date: 2019/8/19 23:33
 * @description: 系统-用户角色表
 */
@Data
@Accessors(chain = true)
@TableName("sys_user_role")
public class SYSUserRole extends BaseEntityUtil implements Serializable {

    @TableId(type = IdType.AUTO)
    private Integer id;   //主键

    private Integer userId;   //用户id

    private Integer roleId;   //角色id

    private Integer deleteFlag;   //删除状态：0：未删除；1：已删除

    private Timestamp createTime;   //创建时间

    private Timestamp updateTime;   //修改时间

}
