package org.huangzi.main.web.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.huangzi.main.common.utils.APIResponse;
import org.huangzi.main.web.entity.DepartmentEntity;

/**
 * @author: XGLLHZ
 * @date: 2020/2/2 下午4:38
 * @description: 部门类事务层接口
 */
public interface DepartmentService extends IService<DepartmentEntity> {

    /**
     * 获取数据列表-分页
     * @param departmentEntity
     * @return
     */
    APIResponse departmentList(DepartmentEntity departmentEntity);

    /**
     * 获取单个数据信息
     * @param departmentEntity
     * @return
     */
    APIResponse getDepartment(DepartmentEntity departmentEntity);

    /**
     * 新增
     * @param departmentEntity
     * @return
     */
    APIResponse insertDepartment(DepartmentEntity departmentEntity);

    /**
     * 删除
     * @param departmentEntity
     * @return
     */
    APIResponse deleteDepartment(DepartmentEntity departmentEntity);

    /**
     * 修改
     * @param departmentEntity
     * @return
     */
    APIResponse updateDepartment(DepartmentEntity departmentEntity);

}
