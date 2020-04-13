package org.huangzi.main.common.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.huangzi.main.common.utils.BaseEntity;

import java.io.Serializable;

/**
 * @author: XGLLHZ
 * @date: 2020/4/13 下午2:32
 * @description: 用户实体类
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("bus_user")
public class UserEntity extends BaseEntity implements Serializable {

    private Integer userType;   //用户类型: 0: 全部； 1: 微信用户; 2: 网页用户; 3: github; 4: csdn; 6: 新浪

    private String unionId;   // unionId

    private String openId;   // openId

    private String nickName;   //微信昵称

    private String avatarUrl;   //头像地址

    private String gender;   //性别: 0: 未知; 1: 男; 2: 女

    private String country;   //国家

    private String province;   //省份

    private String city;   //城市

    private String language;   //所在地区语言

    private String userAccount;   //账号

    private String userPassword;   //密码

    private String otherAccount;   //第三方账号

}
