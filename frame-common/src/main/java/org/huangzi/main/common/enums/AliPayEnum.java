package org.huangzi.main.common.enums;

/**
 * @author: XGLLHZ
 * @date: 2020/2/15 下午5:09
 * @description: 支付宝-支付状态
 */
public enum  AliPayEnum {

    //交易成功
    FINISHED("交易成功", "TRADE_FINISHED"),

    //支付成功
    SUCCESS("支付成功", "TRADE_SUCCESS"),

    //交易创建
    BUYER_PAY("交易创建", "WAIT_BUYER_PAY"),

    //交易关闭
    CLOSED("交易关闭", "TRADE_CLOSED");

    private String value;

    AliPayEnum(String name, String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
