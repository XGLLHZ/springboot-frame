package org.huangzi.main.common.dto;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;
import org.huangzi.main.common.utils.TimeSerialize;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author: XGLLHZ
 * @date: 2020/7/13 下午4:51
 * @description:
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class LogExportDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Excel(name = "用户标识", orderNum = "0")
    private Integer id;

    @Excel(name = "用户名", orderNum = "1", width = 15)
    private String userName;   //用户名

    @Excel(name = "操作名", orderNum = "2", width = 15)
    private String operateName;   //操作名

    @Excel(name = "类名 + 方法名", orderNum = "3", width = 80)
    private String className;   //类名 + 方法名

    @Excel(name = "请求 api", orderNum = "4", width = 30)
    private String requestApi;   //请求 api

    @Excel(name = "请求参数", orderNum = "5", width = 50)
    private String requestParams;   //请求参数

    @Excel(name = "请求耗时", orderNum = "6", suffix = " ms")
    private Long requestTime;   //请求耗时

    @Excel(name = "日志类型", orderNum = "7", replace = { "正常_1", "异常_2" })
    private Integer logType;   //日志类型：1：正常日志；2：异常日志

    @Excel(name = "请求 ip", orderNum = "8", width = 15)
    private String requestIp;   //请求 ip

    @Excel(name = "地址", orderNum = "9", width = 25)
    private String address;   //地址

    @Excel(name = "浏览器", orderNum = "10", width = 25)
    private String browser;   //浏览器

    @Excel(name = "异常详情", orderNum = "11", width = 50)
    private String exceptionDetail;   //异常详情

    @Excel(name = "记录时间", orderNum = "12", width = 20)
    @JsonSerialize(using = TimeSerialize.class)
    private Timestamp createTime;   //创建时间

}
