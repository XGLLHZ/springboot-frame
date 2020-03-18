package org.huangzi.main.common.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.sql.Timestamp;

/**
 * @author: XGLLHZ
 * @date: 2019/11/26 下午3:41
 * @description: 微信授权 access_token 实体类
 */
@Data
@Accessors(chain = true)
/*@TableName("sys_access_token")*/   //创建 sys_access_token 表后，打开此注解
public class AccessToken {

    private Integer id;   //id

    private String accessToken;   //access_token

    private String refreshToken;   //refreshToken

    private Integer deleteFlag;   //删除状态：0：未删除1：已删除

    private Timestamp createTime;   //创建时间

    private Timestamp updateTime;   //修改时间

}
