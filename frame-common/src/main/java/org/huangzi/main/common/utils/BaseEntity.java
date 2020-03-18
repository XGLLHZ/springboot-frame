package org.huangzi.main.common.utils;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author: XGLLHZ
 * @date: 2019/8/20 11:02
 * @description: 实体基础类
 */
@Data
@Accessors(chain = true)
public class BaseEntity implements Serializable {

    @TableId(type = IdType.AUTO)
    private Integer id;   //主键

    private Integer deleteFlag;   //删除状态：0：未删除；1：已删除

    @Excel(name = "发布时间", width = 20, orderNum = "1")
    private Timestamp createTime;   //创建时间

    @Excel(name = "审核时间", width = 20, orderNum = "0")
    private Timestamp updateTime;   //修改时间

    @TableField(exist = false)
    private Integer currentPage = 1;   //分页参数：当前页，默认为1

    @TableField(exist = false)
    private Integer pageSize = 10;   //分页参数：页面大小，默认为10

}
