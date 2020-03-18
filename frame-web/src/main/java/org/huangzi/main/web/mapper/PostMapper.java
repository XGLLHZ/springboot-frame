package org.huangzi.main.web.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.huangzi.main.web.entity.PostEntity;

import java.util.List;

/**
 * @author: XGLLHZ
 * @date: 2020/2/2 下午4:35
 * @description: bus_post 表 mapper 接口
 */
public interface PostMapper extends BaseMapper<PostEntity> {

    /**
     * 获取岗位列表-分页
     * @param page
     * @param postEntity
     * @return
     */
    List<PostEntity> list(Page<PostEntity> page, @Param("condition") PostEntity postEntity);

    /**
     * 获取数据总数
     * @return
     */
    Integer total();

}
