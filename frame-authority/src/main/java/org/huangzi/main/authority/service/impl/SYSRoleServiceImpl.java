package org.huangzi.main.authority.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.huangzi.main.common.dto.ExceptionDto;
import org.huangzi.main.common.utils.APIResponse;
import org.huangzi.main.common.utils.ConstConfig;
import org.huangzi.main.authority.entity.SYSPermRole;
import org.huangzi.main.authority.entity.SYSPermission;
import org.huangzi.main.authority.entity.SYSRole;
import org.huangzi.main.authority.mapper.SYSPermRoleMapper;
import org.huangzi.main.authority.mapper.SYSRoleMapper;
import org.huangzi.main.authority.service.SYSPermRoleService;
import org.huangzi.main.authority.service.SYSRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author: XGLLHZ
 * @date: 2019/8/20 15:19
 * @description:
 */
@Service
@Primary
public class SYSRoleServiceImpl extends ServiceImpl<SYSRoleMapper, SYSRole> implements SYSRoleService {

    @Autowired
    SYSRoleMapper sysRoleMapper;

    @Autowired
    SYSPermRoleMapper sysPermRoleMapper;

    @Autowired
    SYSPermRoleService sysPermRoleService;

    @Override
    public APIResponse list(SYSRole sysRole) {
        Page<SYSRole> page = new Page<>(sysRole.getCurrentPage(), sysRole.getPageSize());
        List<SYSRole> list = sysRoleMapper.list(page, sysRole);
        Integer total = sysRoleMapper.total(sysRole);
        Map<String, Object> data = new HashMap<>();
        data.put("dataList", list);
        data.put("total", total);
        return new APIResponse(data);
    }

    @Override
    public APIResponse get(SYSRole sysRole) {
        SYSRole sysRole1 = sysRoleMapper.selectById(sysRole.getId());
        if (sysRole1 == null) {
            throw new ExceptionDto(ConstConfig.RE_NO_EXIST_ERROR_CODE, ConstConfig.RE_NO_EXIST_ERROR_MESSAGE);
        }
        List<SYSPermission> list = sysRoleMapper.permRoleList(sysRole.getId());
        if (list == null) {
            throw new ExceptionDto(ConstConfig.RE_NO_EXIST_ERROR_CODE, ConstConfig.RE_NO_EXIST_ERROR_MESSAGE);
        }
        List<Integer> ids = list.stream().map(SYSPermission::getId).collect(Collectors.toList());
        Map<String, Object> map = new HashMap<>(1);
        sysRole1.setList(list);
        sysRole1.setPermIds(ids);
        map.put(ConstConfig.DATA_INFO, sysRole1);
        return new APIResponse(map);
    }

    @Override
    @Transactional
    public APIResponse insert(SYSRole sysRole) {
        SYSRole sysRole1 = sysRoleMapper.selectOne(new QueryWrapper<SYSRole>()
                .eq("delete_flag", ConstConfig.DELETE_FLAG_ZONE)
                .eq("role_namey", sysRole.getRoleNamey()));
        if (sysRole1 != null) {
            throw new ExceptionDto(ConstConfig.RE_ALREADY_EXIST_ERROR_CODE, ConstConfig.RE_ALREADY_EXIST_ERROR_MESSAGE);
        }
        int res = sysRoleMapper.insert(sysRole);
        if (res <= 0) {
            throw new ExceptionDto(ConstConfig.RE_ERROR_CODE, ConstConfig.RE_ERROR_MESSAGE);
        }
        if (sysRole.getPermIds() == null || sysRole.getPermIds().size() <= 0) {
            throw new ExceptionDto(ConstConfig.RE_ERROR_CODE, ConstConfig.RE_ERROR_MESSAGE);
        }
        List<SYSPermRole> list = new ArrayList<>();
        SYSPermRole sysPermRole;
        for (int id : sysRole.getPermIds()) {
            sysPermRole = new SYSPermRole();
            sysPermRole.setRoleId(sysRole.getId());
            sysPermRole.setPermId(id);
            list.add(sysPermRole);
        }
        boolean result = sysPermRoleService.saveBatch(list);
        if (!result) {
            throw new ExceptionDto(ConstConfig.RE_ERROR_CODE, ConstConfig.RE_ERROR_MESSAGE);
        }
        return new APIResponse();
    }

    @Override
    @Transactional
    public APIResponse delete(SYSRole sysRole) {
        SYSRole sysRole1 = sysRoleMapper.selectById(sysRole.getId());
        if (sysRole1 == null) {
            throw new ExceptionDto(ConstConfig.RE_NO_EXIST_ERROR_CODE, ConstConfig.RE_NO_EXIST_ERROR_MESSAGE);
        }
        sysRole1.setDeleteFlag(ConstConfig.DELETE_FLAG_ONE);
        int res = sysRoleMapper.updateById(sysRole1);
        if (res <= 0) {
            throw new ExceptionDto(ConstConfig.RE_ERROR_CODE, ConstConfig.RE_ERROR_MESSAGE);
        }
        List<SYSPermRole> list = sysPermRoleMapper.selectList(new QueryWrapper<SYSPermRole>()
                .eq("delete_flag", ConstConfig.DELETE_FLAG_ZONE)
                .eq("role_id", sysRole.getId()));
        if (list == null || list.size() <= 0){
            throw new ExceptionDto(ConstConfig.RE_ERROR_CODE, ConstConfig.RE_ERROR_MESSAGE);
        }
        List<SYSPermRole> list1 = list.stream()
                .peek(s -> s.setDeleteFlag(ConstConfig.DELETE_FLAG_ONE)).collect(Collectors.toList());
        boolean result = sysPermRoleService.updateBatchById(list1);
        if (!result) {
            throw new ExceptionDto(ConstConfig.RE_ERROR_CODE, ConstConfig.RE_ERROR_MESSAGE);
        }
        return new APIResponse();
    }

    @Override
    @Transactional
    public APIResponse update(SYSRole sysRole) {
        SYSRole sysRole1 = sysRoleMapper.selectById(sysRole.getId());
        if (sysRole1 == null) {
            throw new ExceptionDto(ConstConfig.RE_NO_EXIST_ERROR_CODE, ConstConfig.RE_NO_EXIST_ERROR_MESSAGE);
        }
        int res = sysRoleMapper.updateById(sysRole);
        if (res <= 0) {
            throw new ExceptionDto(ConstConfig.RE_ERROR_CODE, ConstConfig.RE_ERROR_MESSAGE);
        }

        List<SYSPermRole> list = sysPermRoleMapper.selectList(new QueryWrapper<SYSPermRole>()
                .eq("delete_flag", ConstConfig.DELETE_FLAG_ZONE)
                .eq("role_id", sysRole.getId()));
        if (list == null || list.size() <= 0){
            throw new ExceptionDto(ConstConfig.RE_ERROR_CODE, ConstConfig.RE_ERROR_MESSAGE);
        }
        List<SYSPermRole> list1 = list.stream()
                .peek(s -> s.setDeleteFlag(ConstConfig.DELETE_FLAG_ONE)).collect(Collectors.toList());
        boolean result = sysPermRoleService.updateBatchById(list1);
        if (!result) {
            throw new ExceptionDto(ConstConfig.RE_ERROR_CODE, ConstConfig.RE_ERROR_MESSAGE);
        }
        if (sysRole.getPermIds() == null || sysRole.getPermIds().size() <= 0) {
            throw new ExceptionDto(ConstConfig.RE_ERROR_CODE, ConstConfig.RE_ERROR_MESSAGE);
        }
        List<SYSPermRole> list2 = new ArrayList<>();
        SYSPermRole sysPermRole;
        for (int id : sysRole.getPermIds()) {
            sysPermRole = new SYSPermRole();
            sysPermRole.setRoleId(sysRole.getId());
            sysPermRole.setPermId(id);
            list2.add(sysPermRole);
        }
        result = sysPermRoleService.saveBatch(list2);
        if (!result) {
            throw new ExceptionDto(ConstConfig.RE_ERROR_CODE, ConstConfig.RE_ERROR_MESSAGE);
        }
        return new APIResponse();
    }

    @Override
    public APIResponse getAllRole() {
        List<SYSRole> list = sysRoleMapper.selectList(new QueryWrapper<SYSRole>()
                .eq("delete_flag", ConstConfig.DELETE_FLAG_ZONE));
        Map<String, Object> map = new HashMap<>(1);
        if (list == null) {
            map.put(ConstConfig.DATA_LIST, new ArrayList<>());
            return new APIResponse(map);
        }
        map.put(ConstConfig.DATA_LIST, list);
        return new APIResponse(map);
    }

}
