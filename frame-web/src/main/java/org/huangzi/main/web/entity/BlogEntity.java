package org.huangzi.main.web.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;
import org.huangzi.main.common.utils.BaseEntityUtil;

import java.io.Serializable;

/**
 * @author: XGLLHZ
 * @date: 2020/2/25 下午6:12
 * @description: 博客实体类
 */
@Data
@Accessors(chain = true)
@TableName("bus_blog")
public class BlogEntity extends BaseEntityUtil implements Serializable {

    @Excel(name = "文章标题", width = 25, orderNum = "0")
    private String blogTitle;   //标题

    @Excel(name = "作者", width = 10, orderNum = "0")
    private String blogAuthor;   //作者

    @Excel(name = "文章类型", width = 10, orderNum = "0")
    private Integer blogType;   //类型: 0: 全部; 1: java;2: python;...

    @Excel(name = "文章标签", width = 20, orderNum = "0")
    private String blogLabel;   //标签

    @Excel(name = "阅读人数", width = 10, orderNum = "0")
    private Integer readNumber;   //阅读人数

    @Excel(name = "评论人数", width = 10, orderNum = "0")
    private Integer commentNumber;   //评论人数

    @Excel(name = "文章状态", width = 10, orderNum = "0")
    private Integer blogStatus;   //状态：0：全部；1：审核中；2：未通过；3：审核通过

    @TableField(exist = false)
    private Integer contentId;   //富文本 id

    @TableField(exist = false)
    private String content;   //富文本内容

    @TableField(exist = false)
    private String fileTitle;   //导出文件名

}
