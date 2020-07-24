package org.huangzi.main.common.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;
import org.huangzi.main.common.utils.TimeSerialize;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author: XGLLHZ
 * @date: 2020/7/20 下午2:49
 * @description: es 日志实体类（添加数据）
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Document(indexName = "sys_log_index")
public class LogEsDto implements Serializable {

    @Id
    private Integer id;

    /**
     * type = FieldType.Text: 支持分词
     * analyzer = "ik_smart": 分析时使用的分词器
     * searchAnalyzer = "ik_smart": 搜索时使用的分词器
     */
    @Field(type = FieldType.Text, analyzer = "ik_smart", searchAnalyzer = "ik_smart")
    private String userName;   //用户名

    @Field(type = FieldType.Text, analyzer = "ik_smart", searchAnalyzer = "ik_smart")
    private String operateName;   //操作名

    @Field(type = FieldType.Text, analyzer = "ik_smart", searchAnalyzer = "ik_smart")
    private String className;   //类名 + 方法名

    @Field(type = FieldType.Text, analyzer = "ik_smart", searchAnalyzer = "ik_smart")
    private String requestApi;   //请求 api

    @Field(type = FieldType.Text, analyzer = "ik_smart", searchAnalyzer = "ik_smart")
    private String requestParams;   //请求参数

    @Field(type = FieldType.Double)
    private Long requestTime;   //请求耗时

    @Field(type = FieldType.Text, analyzer = "ik_smart", searchAnalyzer = "ik_smart")
    private Integer logType;   //日志类型：1：正常日志；2：异常日志

    private String requestIp;   //请求 ip

    @Field(type = FieldType.Text, analyzer = "ik_smart", searchAnalyzer = "ik_smart")
    private String address;   //地址

    @Field(type = FieldType.Text, analyzer = "ik_smart", searchAnalyzer = "ik_smart")
    private String browser;   //浏览器

    private String exceptionDetail;   //异常详情

    private Integer deleteFlag;   //删除状态：0：未删除；1：已删除

    /*@Field(type = FieldType.Text, analyzer = "ik_smart", searchAnalyzer = "ik_smart")
    @JsonSerialize(using = TimeSerialize.class)
    private Timestamp createTime;   //创建时间

    @Field(type = FieldType.Text, analyzer = "ik_smart", searchAnalyzer = "ik_smart")
    @JsonSerialize(using = TimeSerialize.class)
    private Timestamp updateTime;   //修改时间*/

}
