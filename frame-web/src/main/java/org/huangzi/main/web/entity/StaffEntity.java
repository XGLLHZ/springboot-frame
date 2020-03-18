package org.huangzi.main.web.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;
import org.huangzi.main.common.utils.BaseEntity;

import java.io.Serializable;

/**
 * @author: XGLLHZ
 * @date: 2020/1/20 下午5:22
 * @description: 员工实体类
 */
@Data
@Accessors(chain = true)
@TableName("bus_staff")
public class StaffEntity extends BaseEntity implements Serializable {

    private String staffName;   //姓名

    private Integer staffGender;   //性别

    private Integer staffAge;   //年龄

    private String staffMobile;   //电话

    private String staffEmail;   //邮箱

    private Integer departmentId;   //部门

    private Integer postId;   //岗位

}
