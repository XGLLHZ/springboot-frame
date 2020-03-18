package org.huangzi.main.web.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;
import org.huangzi.main.common.utils.BaseEntityUtil;

import java.io.Serializable;

/**
 * @author: XGLLHZ
 * @date: 2020/3/15 下午6:15
 * @description: 用户实体类
 */
@Data
@Accessors(chain = true)
@TableName("bus_user")
public class UserEntity extends BaseEntityUtil implements Serializable {

    private String userAccount;   //账号

    private String userPassword;   //密码

    private Integer registerWay;   //注册方式：0：全部；1：手机号；2：邮箱；3：github；4：csdn；5：微信；6：新浪

    private String otherAccount;   //第三方账号

    private String nickName;   //昵称

    private Integer gender;   //性别：0：全部；1：男；2：女

    private String userLogo;   //用户 log（文件名或地址）

}
