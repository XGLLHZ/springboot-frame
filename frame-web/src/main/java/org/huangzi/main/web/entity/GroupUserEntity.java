package org.huangzi.main.web.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.huangzi.main.common.utils.BaseEntity;

import java.io.Serializable;

/**
 * @author: XGLLHZ
 * @date: 2020/5/19 下午2:23
 * @description: 群组-用户 实体类
 */
@Data
@ToString
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@TableName("bus_group_user")
public class GroupUserEntity extends BaseEntity implements Serializable {

    private Integer groupId;   //群组 id

    private Integer userId;   //用户 id

    private String userName;   //用户名

    private Integer onlineStatus;   //在线状态: 0: 全部; 1: 在线; 2: 离线

}
