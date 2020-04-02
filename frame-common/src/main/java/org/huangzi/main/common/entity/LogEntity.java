package org.huangzi.main.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;
import org.huangzi.main.common.utils.BaseEntity;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author: XGLLHZ
 * @date: 2020/2/6 下午6:50
 * @description: 系统日志实体类
 */
@Data
@Accessors(chain = true)
@TableName("sys_log")
public class LogEntity extends BaseEntity implements Serializable {

    private String userName;   //用户名

    private String operateName;   //操作名

    private String className;   //类名 + 方法名

    private String requestApi;   //请求 api

    private String requestParams;   //请求参数

    private Long requestTime;   //请求耗时

    private Integer logType;   //日志类型：1：正常日志；2：异常日志

    private String requestIp;   //请求 ip

    private String address;   //地址

    private String browser;   //浏览器

    private String exceptionDetail;   //异常详情

    @TableField(exist = false)
    private String[] searchTime;   //查询时间

    @TableField(exist = false)
    private String startTime;   //开始时间

    @TableField(exist = false)
    private String endTime;   //结束时间

}
