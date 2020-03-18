package org.huangzi.main.authority.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.huangzi.main.common.utils.APIResponse;
import org.huangzi.main.common.utils.ConstConfig;
import org.huangzi.main.authority.entity.SYSPermission;
import org.huangzi.main.authority.mapper.SYSPermMapper;
import org.huangzi.main.authority.service.SYSPermService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: XGLLHZ
 * @date: 2019/8/20 15:33
 * @description:
 */
@Service
@Primary
public class SYSPermServiceImpl extends ServiceImpl<SYSPermMapper, SYSPermission> implements SYSPermService {

    @Autowired
    SYSPermMapper sysPermMapper;

    @Override
    public APIResponse list(SYSPermission sysPermission) {
        Page<SYSPermission> page = new Page<>(sysPermission.getCurrentPage(), sysPermission.getPageSize());
        List<SYSPermission> list = sysPermMapper.list(page, sysPermission);
        Integer total = sysPermMapper.total();
        Map<String, Object> data = new HashMap<>();
        data.put("dataList", list);
        data.put("total", total);
        return new APIResponse(data);
    }

    @Override
    public APIResponse get(SYSPermission sysPermission) {
        SYSPermission sysPermission1 = sysPermMapper.selectById(sysPermission.getId());
        Map<String, Object> data = new HashMap<>();
        data.put("dataInfo", sysPermission1);
        return new APIResponse(data);
    }

    @Override
    public APIResponse insert(SYSPermission sysPermission) {
        SYSPermission sysPermission1 = sysPermMapper.getByUrl(sysPermission.getPermUrl());
        if (sysPermission1 != null) {
            return new APIResponse(ConstConfig.RE_ALREADY_EXIST_ERROR_CODE, ConstConfig.RE_ALREADY_EXIST_ERROR_MESSAGE);
        } else {
            sysPermMapper.insert(sysPermission);
            return new APIResponse();
        }
    }

    @Override
    public APIResponse delete(SYSPermission sysPermission) {
        SYSPermission sysPermission1 = sysPermMapper.selectById(sysPermission.getId());
        if (sysPermission1 != null) {
            sysPermission1.setDeleteFlag(ConstConfig.DELETE_FLAG_ONE);
            sysPermMapper.updateById(sysPermission1);
            return new APIResponse();
        } else {
            return new APIResponse(ConstConfig.RE_NO_EXIST_ERROR_CODE, ConstConfig.RE_NO_EXIST_ERROR_MESSAGE);
        }
    }

    @Override
    public APIResponse update(SYSPermission sysPermission) {
        SYSPermission sysPermission1 = sysPermMapper.selectById(sysPermission.getId());
        if (sysPermission1 != null) {
            sysPermMapper.updateById(sysPermission);
            return new APIResponse();
        }else {
            return new APIResponse(ConstConfig.RE_NO_EXIST_ERROR_CODE, ConstConfig.RE_NO_EXIST_ERROR_MESSAGE);
        }
    }

    @Override
    public APIResponse buildMenu(SYSPermission sysPermission) {
        List<SYSPermission> list = sysPermMapper.selectList(new QueryWrapper<SYSPermission>()
                .eq("delete_flag", 0).eq("perm_type", 1)
                .orderByAsc("perm_sort"));   //目录
        List<SYSPermission> list1 = sysPermMapper.selectList(new QueryWrapper<SYSPermission>()
                .eq("delete_flag", 0).eq("perm_type", 2)
                .orderByAsc("perm_sort"));   //菜单
        if (list == null || list1 == null) {
            return new APIResponse(ConstConfig.RE_ERROR_CODE, ConstConfig.RE_ERROR_MESSAGE);
        }
        for (int i = 0; i < list.size(); i++) {
            List<SYSPermission> list2 = new ArrayList<>();
            for (int j = 0; j < list1.size(); j++) {
                if (list.get(i).getId() == list1.get(j).getParentId()) {
                    list2.add(list1.get(j));
                }
            }
            list.get(i).setChildrenList(list2);
        }
        Map<String, Object> map = new HashMap<>();
        map.put("dataList", list);
        return new APIResponse(map);
    }

    @Override
    public APIResponse getAllPerm() {
        List<SYSPermission> list = sysPermMapper.selectList(new QueryWrapper<SYSPermission>()
                .eq("delete_flag", 0));
        Map<String, Object> map = new HashMap<>(1);
        if (list == null) {
            map.put("dataList", new ArrayList<>());
        }
        List<SYSPermission> list1 = new ArrayList<>();
        for (SYSPermission sysPermission : getRootNode(list)) {
            sysPermission = buildTree(sysPermission, list);
            list1.add(sysPermission);
        }
        map.put("dataList", list1);
        return new APIResponse(map);
    }

    private List<SYSPermission> getRootNode(List<SYSPermission> list) {
        List<SYSPermission> list1 = new ArrayList<>();
        for (SYSPermission sysPermission : list) {
            if (sysPermission.getParentId().equals(0)) {
                list1.add(sysPermission);
            }
        }
        return list1;
    }

    private SYSPermission buildTree(SYSPermission sysPermission, List<SYSPermission> list) {
        List<SYSPermission> list1 = new ArrayList<>();
        for (SYSPermission sysPermission1 : list) {
            if (sysPermission1.getParentId().equals(sysPermission.getId())) {
                list1.add(buildTree(sysPermission1, list));
            }
        }
        sysPermission.setChildrenList(list1);
        return sysPermission;
    }

}

