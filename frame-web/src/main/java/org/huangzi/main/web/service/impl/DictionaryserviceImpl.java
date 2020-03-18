package org.huangzi.main.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.huangzi.main.common.utils.APIResponse;
import org.huangzi.main.common.utils.ConstConfig;
import org.huangzi.main.web.mapper.DictionaryMapper;
import org.huangzi.main.web.service.DictionaryService;
import org.huangzi.main.web.entity.DictionaryEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: XGLLHZ
 * @date: 2020/2/3 下午3:02
 * @description: 数据字典事务层实现类
 */
@Service
@Primary
public class DictionaryserviceImpl extends ServiceImpl<DictionaryMapper, DictionaryEntity> implements DictionaryService {

    @Autowired
    DictionaryMapper dictionaryMapper;

    @Override
    public APIResponse dictList(DictionaryEntity dictionaryEntity) {
        Page<DictionaryEntity> page = new Page<>(dictionaryEntity.getCurrentPage(), dictionaryEntity.getPageSize());
        List<DictionaryEntity> list = dictionaryMapper.list(page, dictionaryEntity);
        Integer total = dictionaryMapper.total();
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
    public APIResponse getDict(DictionaryEntity dictionaryEntity) {
        DictionaryEntity dictionaryEntity1 = dictionaryMapper.selectById(dictionaryEntity.getId());
        if (dictionaryEntity1 == null) {
            return new APIResponse(ConstConfig.RE_NO_EXIST_ERROR_CODE, ConstConfig.RE_NO_EXIST_ERROR_MESSAGE);
        }
        Map<String, Object> map = new HashMap<>();
        if (dictionaryEntity1.getParentId() == 0) {
            List<DictionaryEntity> list = dictionaryMapper.selectList(new QueryWrapper<DictionaryEntity>()
                    .eq("delete_flag", 0)
                    .eq("parent_id", dictionaryEntity1.getId()));
            if (list == null) {
                list = new ArrayList<>();
            }
            dictionaryEntity1.setDictList(list);
            map.put("dataInfo", dictionaryEntity1);
        } else {
            map.put("dataInfo", dictionaryEntity1);
        }
        return new APIResponse(map);
    }

    @Override
    public APIResponse insertDict(DictionaryEntity dictionaryEntity) {
        Integer res = 0;
        if (dictionaryEntity.getParentId() == null || dictionaryEntity.getParentId() == 0) {
            DictionaryEntity dictionaryEntity1 = dictionaryMapper.selectOne(new QueryWrapper<DictionaryEntity>()
                    .eq("delete_flag", 0)
                    .eq("dict_name", dictionaryEntity.getDictName()));
            if (dictionaryEntity1 != null) {
                return new APIResponse(ConstConfig.RE_ALREADY_EXIST_ERROR_CODE, ConstConfig.RE_ALREADY_EXIST_ERROR_MESSAGE);
            }
            res = dictionaryMapper.insert(dictionaryEntity);
        } else {
            DictionaryEntity dictionaryEntity1 = dictionaryMapper.selectById(dictionaryEntity.getId());
            if (dictionaryEntity1 == null) {
                return new APIResponse(ConstConfig.RE_NO_EXIST_ERROR_CODE, ConstConfig.RE_NO_EXIST_ERROR_MESSAGE);
            }
            res = dictionaryMapper.insert(dictionaryEntity);
        }
        if (res < 0) {
            return new APIResponse(ConstConfig.RE_ERROR_CODE, ConstConfig.RE_ERROR_MESSAGE);
        }
        return new APIResponse();
    }

    @Override
    public APIResponse deleteDict(DictionaryEntity dictionaryEntity) {
        DictionaryEntity dictionaryEntity1 = dictionaryMapper.selectById(dictionaryEntity.getId());
        if (dictionaryEntity1 == null) {
            return new APIResponse(ConstConfig.RE_NO_EXIST_ERROR_CODE, ConstConfig.RE_NO_EXIST_ERROR_MESSAGE);
        }
        Integer res = 0;
        if (dictionaryEntity1.getParentId() != 0) {
            dictionaryEntity1.setDeleteFlag(ConstConfig.DELETE_FLAG_ONE);
            res = dictionaryMapper.updateById(dictionaryEntity1);
        } else {
            List<DictionaryEntity> list = dictionaryMapper.selectList(new QueryWrapper<DictionaryEntity>()
                    .eq("delete_flag", 0)
                    .eq("parent_id", dictionaryEntity1.getId()));
            if (list.size() == 0 || list == null) {
                dictionaryEntity1.setDeleteFlag(ConstConfig.DELETE_FLAG_ONE);
                res = dictionaryMapper.updateById(dictionaryEntity1);
            } else {
                return new APIResponse(ConstConfig.RE_DELETE_DICT_ERROR_CODE, ConstConfig.RE_DELETE_DICT_ERROR_MESSAGE);
            }
        }
        if (res < 0) {
            return new APIResponse(ConstConfig.RE_ERROR_CODE, ConstConfig.RE_ERROR_MESSAGE);
        }
        return new APIResponse();
    }

    @Override
    public APIResponse updateDict(DictionaryEntity dictionaryEntity) {
        DictionaryEntity dictionaryEntity1 = dictionaryMapper.selectById(dictionaryEntity.getId());
        if (dictionaryEntity1 == null) {
            return new APIResponse(ConstConfig.RE_NO_EXIST_ERROR_CODE, ConstConfig.RE_NO_EXIST_ERROR_MESSAGE);
        }
        Integer res = 0;
        if (dictionaryEntity1.getParentId() != 0) {
            DictionaryEntity dictionaryEntity2 = dictionaryMapper.selectOne(new QueryWrapper<DictionaryEntity>()
                    .eq("delete_flag", 0)
                    .eq("id", dictionaryEntity1.getParentId()));
            if (dictionaryEntity2 == null) {
                return new APIResponse(ConstConfig.RE_ERROR_CODE, ConstConfig.RE_ERROR_MESSAGE);
            }
            res = dictionaryMapper.updateById(dictionaryEntity);
        } else {
            res = dictionaryMapper.updateById(dictionaryEntity);
        }
        if (res < 0) {
            return new APIResponse(ConstConfig.RE_ERROR_CODE, ConstConfig.RE_ERROR_MESSAGE);
        }
        return new APIResponse();
    }

}
