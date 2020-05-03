package org.huangzi.main.common.entity;

import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;
import org.huangzi.main.common.utils.BaseEntity;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author: XGLLHZ
 * @date: 2020/2/15 下午4:07
 * @description: 订单实体类 这里尽供测试用
 */
@Data
@ToString
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
//@TableName("bus_trade")
public class OrderEntity extends BaseEntity implements Serializable {

    private String payType;   //支付方式：1：支付宝；2：微信

    private String tradeNo;   //第三方交易号

    private String outTradeNo;   //订单号

    private String shopName;   //商品名称

    private String shopPrice;   //商品价格

    private String shopBody;   //商品描述

    private Integer orderStatus;   //订单状态：1：未完成；2：已完成；3：已取消

}
