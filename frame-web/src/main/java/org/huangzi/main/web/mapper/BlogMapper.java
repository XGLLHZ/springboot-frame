package org.huangzi.main.web.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.huangzi.main.web.entity.BlogEntity;

import java.util.List;

/**
 * @author: XGLLHZ
 * @date: 2020/3/9 下午7:36
 * @description: 博客表 mapper 接口
 */
public interface BlogMapper extends BaseMapper<BlogEntity> {

    /**
     * 获取数据列表-分页
     * @param page
     * @param blogEntity
     * @return
     */
    List<BlogEntity> getList(Page<BlogEntity> page, @Param("condition") BlogEntity blogEntity);

    /**
     * 获取数据总数
     * @param blogEntity
     * @return
     */
    Integer getTotal(@Param("condition") BlogEntity blogEntity);

}
