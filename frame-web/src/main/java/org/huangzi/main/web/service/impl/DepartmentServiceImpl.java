package org.huangzi.main.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.huangzi.main.common.utils.APIResponse;
import org.huangzi.main.common.utils.ConstConfig;
import org.huangzi.main.web.mapper.DepartmentMapper;
import org.huangzi.main.web.service.DepartmentService;
import org.huangzi.main.web.entity.DepartmentEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: XGLLHZ
 * @date: 2020/2/2 下午4:40
 * @description: 部门类事务层实现类
 */
@Service
@Primary
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, DepartmentEntity> implements DepartmentService {

    @Autowired
    DepartmentMapper departmentMapper;

    @Override
    public APIResponse departmentList(DepartmentEntity departmentEntity) {
        Page<DepartmentEntity> page = new Page<>(departmentEntity.getCurrentPage(), departmentEntity.getPageSize());
        List<DepartmentEntity> list = departmentMapper.list(page, departmentEntity);
        Integer total = departmentMapper.total();
        Map<String, Object> map = new HashMap<>();
        if (list == null) {
            map.put("dataList", new ArrayList<>());
            map.put("total", 0);
        }
        map.put("dataList", list);
        map.put("total", total);
        return new APIResponse(map);
    }

    @Override
    public APIResponse getDepartment(DepartmentEntity departmentEntity) {
        DepartmentEntity departmentEntity1 = departmentMapper.selectById(departmentEntity.getId());
        if (departmentEntity1 == null) {
            return new APIResponse(ConstConfig.RE_NO_EXIST_ERROR_CODE, ConstConfig.RE_NO_EXIST_ERROR_MESSAGE);
        }
        Map<String, Object> map = new HashMap<>();
        map.put("dataInfo", departmentEntity1);
        return new APIResponse(map);
    }

    @Override
    public APIResponse insertDepartment(DepartmentEntity departmentEntity) {
        DepartmentEntity departmentEntity1 = departmentMapper.selectOne(new QueryWrapper<DepartmentEntity>()
                .eq("delete_flag", 0)
                .eq("department_code", departmentEntity.getDepartmentCode()));
        if (departmentEntity1 != null) {
            return new APIResponse(ConstConfig.RE_CODE_ALREADY_EXIST_ERROR_CODE,
                    ConstConfig.RE_CODE_ALREADY_EXIST_ERROR_MESSAGE);
        }
        Integer res = departmentMapper.insert(departmentEntity);
        if (res < 0) {
            return new APIResponse(ConstConfig.RE_ERROR_CODE, ConstConfig.RE_ERROR_MESSAGE);
        }
        return new APIResponse();
    }

    @Override
    public APIResponse deleteDepartment(DepartmentEntity departmentEntity) {
        DepartmentEntity departmentEntity1 = departmentMapper.selectById(departmentEntity.getId());
        if (departmentEntity1 == null) {
            return new APIResponse(ConstConfig.RE_NO_EXIST_ERROR_CODE, ConstConfig.RE_NO_EXIST_ERROR_MESSAGE);
        }
        departmentEntity1.setDeleteFlag(ConstConfig.DELETE_FLAG_ONE);
        Integer res = departmentMapper.updateById(departmentEntity1);
        if (res < 0) {
            return new APIResponse(ConstConfig.RE_ERROR_CODE, ConstConfig.RE_ERROR_MESSAGE);
        }
        return new APIResponse();
    }

    @Override
    public APIResponse updateDepartment(DepartmentEntity departmentEntity) {
        DepartmentEntity departmentEntity1 = departmentMapper.selectOne(new QueryWrapper<DepartmentEntity>()
                .eq("delete_flag", 0)
                .eq("department_code", departmentEntity.getDepartmentCode()));
        if (departmentEntity1 != null) {
            return new APIResponse(ConstConfig.RE_CODE_ALREADY_EXIST_ERROR_CODE,
                    ConstConfig.RE_CODE_ALREADY_EXIST_ERROR_MESSAGE);
        }
        Integer res = departmentMapper.updateById(departmentEntity);
        if (res < 0) {
            return new APIResponse(ConstConfig.RE_ERROR_CODE, ConstConfig.RE_ERROR_MESSAGE);
        }
        return new APIResponse();
    }

}
