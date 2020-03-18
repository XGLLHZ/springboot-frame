package org.huangzi.main.web.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.huangzi.main.web.entity.DepartmentEntity;

import java.util.List;

/**
 * @author: XGLLHZ
 * @date: 2020/2/2 下午4:34
 * @description: bus_department 表 mapper 接口
 */
public interface DepartmentMapper extends BaseMapper<DepartmentEntity> {

    /**
     * 获取部门列表（分页）
     * @param page
     * @param departmentEntity
     * @return
     */
    List<DepartmentEntity> list(Page<DepartmentEntity> page, @Param("condition") DepartmentEntity departmentEntity);

    /**
     * 获取数据总数
     * @return
     */
    Integer total();

}
