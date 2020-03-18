package org.huangzi.main.authority.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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

import java.util.*;

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

    SYSPermRoleService sysPermRoleService;

    @Override
    public APIResponse list(SYSRole sysRole) {
        Page<SYSRole> page = new Page<>(sysRole.getCurrentPage(), sysRole.getPageSize());
        List<SYSRole> list = sysRoleMapper.list(page, sysRole);
        Integer total = sysRoleMapper.total();
        Map<String, Object> data = new HashMap<>();
        data.put("dataList", list);
        data.put("total", total);
        return new APIResponse(data);
    }

    @Override
    public APIResponse get(SYSRole sysRole) {
        SYSRole sysRole1 = sysRoleMapper.selectById(sysRole.getId());
        if (sysRole1 != null) {
            Map<String, Object> data = new HashMap<>();
            List<SYSPermission> list = sysRoleMapper.permRoleList(sysRole.getId());
            data.put("dataInfo", sysRole1);
            data.put("dataList", list);
            return new APIResponse(data);
        } else {
            return new APIResponse(ConstConfig.RE_NO_EXIST_ERROR_CODE, ConstConfig.RE_NO_EXIST_ERROR_MESSAGE);
        }
    }

    @Override
    public APIResponse insert(SYSRole sysRole) {
        SYSRole sysRole1 = sysRoleMapper.selectOne(
                new QueryWrapper<SYSRole>().eq("role_namey", sysRole.getRoleNamey()));
        if (sysRole1 != null) {
            return new APIResponse(ConstConfig.RE_ALREADY_EXIST_ERROR_CODE, ConstConfig.RE_ALREADY_EXIST_ERROR_MESSAGE);
        } else {
            sysRoleMapper.insert(sysRole);
            List<SYSPermRole> list = new ArrayList<>();
            if (sysRole.getPermIds() != null && sysRole.getPermIds().length > 0) {
                for (int permId : sysRole.getPermIds()) {
                    SYSPermRole sysPermRole = new SYSPermRole();
                    sysPermRole.setPermId(permId);
                    sysPermRole.setRoleId(sysRole.getId());
                    list.add(sysPermRole);
                }
                boolean res = sysPermRoleService.saveBatch(list, 30);
                if (res) {
                    return new APIResponse();
                } else {
                    return new APIResponse(ConstConfig.RE_ERROR_CODE, ConstConfig.RE_ERROR_MESSAGE);
                }
            }
            return new APIResponse();
        }
    }

    @Override
    public APIResponse delete(SYSRole sysRole) {
        SYSRole sysRole1 = sysRoleMapper.selectById(sysRole.getId());
        if (sysRole1 != null) {
            sysRole1.setDeleteFlag(ConstConfig.DELETE_FLAG_ONE);
            sysRoleMapper.updateById(sysRole1);
            List<SYSPermRole> list = sysPermRoleMapper.selectList(
                    new QueryWrapper<SYSPermRole>().eq("role_id", sysRole.getId()));
            if (list != null && list.size() > 0) {
                for (SYSPermRole sysPermRole : list) {
                    sysPermRole.setDeleteFlag(ConstConfig.DELETE_FLAG_ONE);
                }
                boolean res = sysPermRoleService.updateBatchById(list);
                if (res) {
                    return new APIResponse();
                } else {
                    return new APIResponse(ConstConfig.RE_ERROR_CODE, ConstConfig.RE_ERROR_MESSAGE);
                }
            }
            return new APIResponse();
        } else {
            return new APIResponse(ConstConfig.RE_NO_EXIST_ERROR_CODE, ConstConfig.RE_NO_EXIST_ERROR_MESSAGE);
        }
    }

    @Override
    public APIResponse update(SYSRole sysRole) {
        SYSRole sysRole1 = sysRoleMapper.selectById(sysRole.getId());
        if (sysRole1 != null) {
            sysRoleMapper.updateById(sysRole);
            if (sysRole.getPermIds() != null && sysRole.getPermIds().length > 0) {
                List<SYSPermRole> list = sysPermRoleMapper.selectList(
                        new QueryWrapper<SYSPermRole>().eq("role_id", sysRole.getId()));
                for (SYSPermRole sysPermRole : list) {
                    sysPermRole.setDeleteFlag(ConstConfig.DELETE_FLAG_ONE);
                }
                boolean res = sysPermRoleService.updateBatchById(list, 30);
                if (res) {
                    list.clear();
                    for (int permId : sysRole.getPermIds()) {
                        SYSPermRole sysPermRole = new SYSPermRole();
                        sysPermRole.setRoleId(sysRole.getId());
                        sysPermRole.setPermId(permId);
                        list.add(sysPermRole);
                    }
                    boolean result = sysPermRoleService.saveBatch(list);
                    if (result) {
                        return new APIResponse();
                    } else {
                        return new APIResponse(ConstConfig.RE_ERROR_CODE, ConstConfig.RE_ERROR_MESSAGE);
                    }
                }
            }
            return new APIResponse();
        } else {
            return new APIResponse(ConstConfig.RE_NO_EXIST_ERROR_CODE, ConstConfig.RE_NO_EXIST_ERROR_MESSAGE);
        }
    }

}
