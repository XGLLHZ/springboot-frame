package org.huangzi.main.common.entity;

import lombok.Data;
import lombok.experimental.Accessors;
import org.huangzi.main.common.utils.BaseEntity;

/**
 * @author: XGLLHZ
 * @date: 2020/2/4 下午5:27
 * @description: 在线用户实体类
 */
@Data
@Accessors(chain = true)
public class OnlineUserEntity extends BaseEntity {

    private Integer id;   //用户 id

    private String userName;   //用户名

    private Integer userType;   //用户类型：1：超级管理员；2：管理员；业务员

    private String loginIp;   //登录 ip

    private String loginAddress;   //登录地点

    private String loginBrowser;   //登录浏览器

    private String loginTime;   //登录时间

}
