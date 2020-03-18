package org.huangzi.main.common.utils;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Collections;
import java.util.Map;

/**
 * @author: XGLLHZ
 * @date: 2019/8/20 11:07
 * @description: 返回基础类
 */
@Data
@Accessors(chain = true)
public class APIResponse<T> {

    private Integer recode;   //返回状态码

    private String remsg;   //返回消息体

    private T body;   //返回数据体

    /**
     * 请求成功-无数据
     */
    public APIResponse() {
        this.recode = ConstConfig.RE_SUCCESS_CODE;
        this.remsg = ConstConfig.RE_SUCCESS_MESSAGE;
        body = (T) Collections.EMPTY_MAP;
    }

    /**
     * 请求成功-有数据
     * @param data
     */
    public APIResponse(Map<String, Object> data) {
        this.recode = ConstConfig.RE_SUCCESS_CODE;
        this.remsg = ConstConfig.RE_SUCCESS_MESSAGE;
        body = (T) data;
    }

    /**
     * 请求失败
     * @param errorCode 错误状态码（自定义）
     * @param errorMsg 错误消息体（自定义）
     */
    public APIResponse(int errorCode, String errorMsg) {
        this.recode = errorCode;
        this.remsg = errorMsg;
        body = (T) Collections.EMPTY_MAP;
    }

}
