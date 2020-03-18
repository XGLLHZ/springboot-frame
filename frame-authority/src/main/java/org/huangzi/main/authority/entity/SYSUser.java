package org.huangzi.main.authority.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;
import org.huangzi.main.common.utils.BaseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author: XGLLHZ
 * @date: 2019/8/19 23:04
 * @description: 系统-用户表
 */
@Data
@Accessors(chain = true)
@TableName("sys_user")
public class SYSUser extends BaseEntity implements UserDetails, Serializable {

    @TableId(type = IdType.AUTO)
    private Integer id;   //主键

    private String username;   //账号（用户名）

    private String password;   //密码

    private Integer userType;   //用户类型：1：超级管理员；2：管理员；业务员

    private Integer deleteFlag;   //删除状态：0：未删除；1：已删除

    private Timestamp createTime;   //创建时间

    private Timestamp updateTime;   //修改时间

    @TableField(exist = false)
    private int[] roleIds;   //用户对应的角色id数组

    @TableField(exist = false)
    private List<SYSRole> list;   //用户所具有的角色

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        if (list != null) {
            for (SYSRole role : list) {
                authorities.add(new SimpleGrantedAuthority(role.getRoleNamey()));
            }
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
