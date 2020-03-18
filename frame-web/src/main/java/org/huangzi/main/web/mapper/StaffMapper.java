package org.huangzi.main.web.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.huangzi.main.web.entity.StaffEntity;

import java.util.List;

/**
 * @author: XGLLHZ
 * @date: 2020/2/2 下午4:36
 * @description: bus_staff 表 mapper 接口
 */
public interface StaffMapper extends BaseMapper<StaffEntity> {

    /**
     * 获取员工列表-分页
     * @param page
     * @param staffEntity
     * @return
     */
    List<StaffEntity> list(Page<StaffEntity> page, @Param("condition") StaffEntity staffEntity);

    /**
     * 获取数据总数
     * @return
     */
    Integer total();

}
