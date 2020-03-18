package org.huangzi.main.common.entity;

import lombok.Data;
import lombok.experimental.Accessors;
import org.huangzi.main.common.utils.BaseEntityUtil;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author: XGLLHZ
 * @date: 2020/2/15 下午4:07
 * @description: 订单实体类 这里尽供测试用
 */
@Data
@Accessors(chain = true)
//@TableName("bus_trade")
public class OrderEntity extends BaseEntityUtil implements Serializable {

    //@TableId(type = IdType.AUTO)
    //private Long id;   //主键

    private String payType;   //支付方式：1：支付宝；2：微信

    private String tradeNo;   //第三方交易号

    private String outTradeNo;   //订单号

    private String shopName;   //商品名称

    private String shopPrice;   //商品价格

    private String shopBody;   //商品描述

    private Integer orderStatus;   //订单状态：1：未完成；2：已完成；3：已取消

    private Integer deleteFlag;   //删除状态：0：未删除；1：已删除

    private Timestamp createTime;   //创建时间

    private Timestamp updateTime;   //修改时间

}
