package org.huangzi.main.web.service.impl;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.huangzi.main.common.utils.APIResponse;
import org.huangzi.main.common.utils.ConstConfig;
import org.huangzi.main.web.mapper.DepartmentMapper;
import org.huangzi.main.web.mapper.StaffMapper;
import org.huangzi.main.web.mapper.PostMapper;
import org.huangzi.main.web.service.StaffService;
import org.huangzi.main.web.entity.DepartmentEntity;
import org.huangzi.main.web.entity.PostEntity;
import org.huangzi.main.web.entity.StaffEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author: XGLLHZ
 * @date: 2020/2/2 下午4:42
 * @description: 员工类事务层实现类
 */
@Service
@Primary
public class StaffServiceImpl extends ServiceImpl<StaffMapper, StaffEntity> implements StaffService {

    @Autowired
    StaffMapper staffMapper;

    @Autowired
    DepartmentMapper departmentMapper;

    @Autowired
    PostMapper postMapper;

    @Override
    public APIResponse staffList(StaffEntity staffEntity) {
        Page<StaffEntity> page = new Page<>(staffEntity.getCurrentPage(), staffEntity.getPageSize());
        List<StaffEntity> list = staffMapper.list(page, staffEntity);
        Integer total = staffMapper.total();
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
    public APIResponse getStaff(StaffEntity staffEntity) {
        StaffEntity staffEntity1 = staffMapper.selectById(staffEntity.getId());
        if (staffEntity1 == null) {
            return new APIResponse(ConstConfig.RE_NO_EXIST_ERROR_CODE, ConstConfig.RE_NO_EXIST_ERROR_MESSAGE);
        }
        Map<String, Object> map = new HashMap<>();
        map.put("dataInfo", staffEntity1);
        return new APIResponse(map);
    }

    @Override
    public APIResponse insertStaff(StaffEntity staffEntity) {
        DepartmentEntity departmentEntity = departmentMapper.selectById(staffEntity.getDepartmentId());
        if (departmentEntity == null) {
            return new APIResponse(ConstConfig.RE_DEPARTMENT_NO_EXIST_ERROR_CODE, ConstConfig.RE_DEPARTMENT_NO_EXIST_ERROR_MESSAGE);
        }
        PostEntity postEntity = postMapper.selectById(staffEntity.getPostId());
        if (postEntity == null) {
            return new APIResponse(ConstConfig.RE_POST_NO_EXIST_ERROR_CODE, ConstConfig.RE_PSOT_NO_EXIST_ERROR_MESSAGE);
        }
        Integer res = staffMapper.insert(staffEntity);
        if (res < 0) {
            return new APIResponse(ConstConfig.RE_ERROR_CODE, ConstConfig.RE_ERROR_MESSAGE);
        }
        return new APIResponse();
    }

    @Override
    public APIResponse deleteStaff(StaffEntity staffEntity) {
        StaffEntity staffEntity1 = staffMapper.selectById(staffEntity.getId());
        if (staffEntity1 == null) {
            return new APIResponse(ConstConfig.RE_NO_EXIST_ERROR_CODE, ConstConfig.RE_NO_EXIST_ERROR_MESSAGE);
        }
        staffEntity1.setDeleteFlag(ConstConfig.DELETE_FLAG_ONE);
        Integer res = staffMapper.updateById(staffEntity);
        if (res < 0) {
            return new APIResponse(ConstConfig.RE_ERROR_CODE, ConstConfig.RE_ERROR_MESSAGE);
        }
        return new APIResponse();
    }

    @Override
    public APIResponse updateStaff(StaffEntity staffEntity) {
        StaffEntity staffEntity1 = staffMapper.selectById(staffEntity.getId());
        if (staffEntity1 == null) {
            return new APIResponse(ConstConfig.RE_NO_EXIST_ERROR_CODE, ConstConfig.RE_NO_EXIST_ERROR_MESSAGE);
        }
        DepartmentEntity departmentEntity = departmentMapper.selectById(staffEntity.getDepartmentId());
        if (departmentEntity == null) {
            return new APIResponse(ConstConfig.RE_DEPARTMENT_NO_EXIST_ERROR_CODE, ConstConfig.RE_DEPARTMENT_NO_EXIST_ERROR_MESSAGE);
        }
        PostEntity postEntity = postMapper.selectById(staffEntity.getPostId());
        if (postEntity == null) {
            return new APIResponse(ConstConfig.RE_POST_NO_EXIST_ERROR_CODE, ConstConfig.RE_PSOT_NO_EXIST_ERROR_MESSAGE);
        }
        Integer res = staffMapper.updateById(staffEntity);
        if (res < 0) {
            return new APIResponse(ConstConfig.RE_ERROR_CODE, ConstConfig.RE_ERROR_MESSAGE);
        }
        return new APIResponse();
    }

}
