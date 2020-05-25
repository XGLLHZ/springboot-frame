package org.huangzi.main.wx.mp.entity;

import lombok.*;
import org.huangzi.main.common.utils.BaseEntity;

import java.io.Serializable;

/**
 * @author: XGLLHZ
 * @date: 2020/5/22 下午11:31
 * @description: 公众号用户实体类
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class MpUserDto extends BaseEntity implements Serializable {

    private String unionId;   // unionId

    private String openId;   //openId

    private String nickName;   //微信名

    private String headImgUrl;   //微信头像地址

    private Boolean subscribe;   //是否关注

    private Long subscribeTime;   //关注时间

    private Integer sex;   //性别: 0: 未知; 1: 男; 2: 女

    private String country;   //国家

    private String province;   //省份

    private String city;   //城市

    private String language;   //语言

}
