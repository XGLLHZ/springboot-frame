package org.huangzi.main.web.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;
import org.huangzi.main.common.utils.BaseEntityUtil;

import java.io.Serializable;

/**
 * @author: XGLLHZ
 * @date: 2020/1/20 下午5:35
 * @description: 部门实体类
 */
@Data
@Accessors(chain = true)
@TableName("bus_department")
public class DepartmentEntity extends BaseEntityUtil implements Serializable {

    private Integer parentId;   //上级部门 id

    private String departmentName;   //部门名称

    private String departmentCode;   //部门代码

    private Integer departmentType;   //部门类型：1：一级部门；2：二级部门

}
