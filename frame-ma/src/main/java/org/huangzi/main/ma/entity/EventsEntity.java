package org.huangzi.main.ma.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.huangzi.main.common.utils.BaseEntity;

import java.io.Serializable;

/**
 * @author: XGLLHZ
 * @date: 2020/4/13 上午11:27
 * @description: 事件实体类
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("ma_events")
public class EventsEntity extends BaseEntity implements Serializable {

    private String userId;   //用户 id

    private String eventTime;   //事件时间

    private String eventContent;   //事件内容

}
