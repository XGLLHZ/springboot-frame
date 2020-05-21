package org.huangzi.main.web.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.huangzi.main.common.utils.BaseEntity;

import java.io.Serializable;
import java.util.List;

/**
 * @author: XGLLHZ
 * @date: 2020/5/19 下午2:16
 * @description: 用户群组
 */
@Data
@ToString
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@TableName("bus_group")
public class GroupEntity extends BaseEntity implements Serializable {

    private String groupName;   //群名称

    private Integer groupSize;   //成员数量

    @TableField(exist = false)
    private Integer userId;   //用户 id

    @TableField(exist = false)
    private List<GroupUserEntity> userList;   //成员列表

    @TableField(exist = false)
    private Integer onlineNum;   //在线人数

    @TableField(exist = false)
    private Integer offlineNum;   //离线人数

}
