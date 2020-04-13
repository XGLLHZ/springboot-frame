package org.huangzi.main.ma.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.huangzi.main.common.dto.ExceptionDto;
import org.huangzi.main.common.utils.APIResponse;
import org.huangzi.main.common.utils.ConstConfig;
import org.huangzi.main.ma.entity.DynamicEntity;
import org.huangzi.main.ma.entity.MediaEntity;
import org.huangzi.main.ma.mapper.DynamicMapper;
import org.huangzi.main.ma.mapper.MediaMapper;
import org.huangzi.main.ma.service.DynamicService;
import org.huangzi.main.ma.service.MediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: XGLLHZ
 * @date: 2020/4/13 下午4:37
 * @description: 动态事务层实现类
 */
@Primary
@Service("DynamicServiceImpl")
public class DynamicServiceImpl extends ServiceImpl<DynamicMapper, DynamicEntity> implements DynamicService {

    @Autowired
    private DynamicMapper dynamicMapper;

    @Autowired
    private MediaMapper mediaMapper;

    @Autowired
    private MediaService mediaService;

    @Override
    public APIResponse getList(DynamicEntity dynamicEntity) {
        Page<DynamicEntity> page = new Page<>(dynamicEntity.getCurrentPage(), dynamicEntity.getPageSize());
        List<DynamicEntity> list = dynamicMapper.getList(page, dynamicEntity);
        Integer total = dynamicMapper.getTotal(dynamicEntity);
        Map<String, Object> map = new HashMap<>(2);
        if (list == null || total == null) {
            map.put(ConstConfig.DATA_LIST, new ArrayList<>());
            map.put(ConstConfig.TOTAL, 0);
            return new APIResponse(map);
        }
        map.put(ConstConfig.DATA_LIST, list);
        map.put(ConstConfig.TOTAL, total);
        return new APIResponse(map);
    }

    @Override
    @Transactional
    public APIResponse insertDynamic(DynamicEntity dynamicEntity) {
        int res = dynamicMapper.insert(dynamicEntity);
        if (res <= 0) {
            throw new ExceptionDto(ConstConfig.RE_ERROR_CODE, ConstConfig.RE_ERROR_MESSAGE);
        }
        if (dynamicEntity.getFileList() != null && dynamicEntity.getFileList().size() > 0) {
            List<MediaEntity> list = new ArrayList<>();
            MediaEntity mediaEntity = new MediaEntity();
            mediaEntity.setObjectId(dynamicEntity.getId());
            mediaEntity.setUserId(dynamicEntity.getUserId());
            mediaEntity.setMediaType(1);   //图片
            for (String fileName : dynamicEntity.getFileList()) {
                mediaEntity.setFileName(fileName);
                list.add(mediaEntity);
            }
            boolean result = mediaService.saveBatch(list);
            if (!result) {
                throw new ExceptionDto(ConstConfig.RE_ERROR_CODE, ConstConfig.RE_ERROR_MESSAGE);
            }
        }
        return new APIResponse();
    }

    @Override
    @Transactional
    public APIResponse deleteDynamic(DynamicEntity dynamicEntity) {
        DynamicEntity dynamicEntity1 = dynamicMapper.selectById(dynamicEntity.getId());
        if (dynamicEntity1 == null) {
            return new APIResponse(ConstConfig.RE_NO_EXIST_ERROR_CODE, ConstConfig.RE_NO_EXIST_ERROR_MESSAGE);
        }
        dynamicEntity1.setDeleteFlag(ConstConfig.DELETE_FLAG_ONE);
        int res = dynamicMapper.updateById(dynamicEntity1);
        if (res <= 0) {
            throw new ExceptionDto(ConstConfig.RE_ERROR_CODE, ConstConfig.RE_ERROR_MESSAGE);
        }
        List<MediaEntity> list = mediaMapper.selectList(new QueryWrapper<MediaEntity>()
                .eq("delete_flag", ConstConfig.DELETE_FLAG_ZONE)
                .eq("object_id", dynamicEntity.getId()));
        if (list != null && list.size() > 0) {
            for (MediaEntity mediaEntity : list) {
                mediaEntity.setDeleteFlag(ConstConfig.DELETE_FLAG_ONE);
            }
            boolean result = mediaService.updateBatchById(list);
            if (!result) {
                throw new ExceptionDto(ConstConfig.RE_ERROR_CODE, ConstConfig.RE_ERROR_MESSAGE);
            }
        }
        return new APIResponse();
    }

    @Override
    public APIResponse updateDynamic(DynamicEntity dynamicEntity) {
        DynamicEntity dynamicEntity1 = dynamicMapper.selectById(dynamicEntity.getId());
        if (dynamicEntity1 == null) {
            return new APIResponse(ConstConfig.RE_NO_EXIST_ERROR_CODE, ConstConfig.RE_NO_EXIST_ERROR_MESSAGE);
        }
        int res = dynamicMapper.updateById(dynamicEntity1);
        if (res <= 0) {
            throw new ExceptionDto(ConstConfig.RE_ERROR_CODE, ConstConfig.RE_ERROR_MESSAGE);
        }
        List<MediaEntity> list = mediaMapper.selectList(new QueryWrapper<MediaEntity>()
                .eq("delete_flag", ConstConfig.DELETE_FLAG_ZONE)
                .eq("object_id", dynamicEntity.getId()));
        if (list != null && list.size() > 0) {
            for (MediaEntity mediaEntity : list) {
                mediaEntity.setDeleteFlag(ConstConfig.DELETE_FLAG_ONE);
            }
            boolean result = mediaService.updateBatchById(list);
            if (!result) {
                throw new ExceptionDto(ConstConfig.RE_ERROR_CODE, ConstConfig.RE_ERROR_MESSAGE);
            }
        }
        if (dynamicEntity.getFileList() != null && dynamicEntity.getFileList().size() > 0) {
            List<MediaEntity> list1 = new ArrayList<>();
            MediaEntity mediaEntity = new MediaEntity();
            mediaEntity.setObjectId(dynamicEntity.getId());
            mediaEntity.setUserId(dynamicEntity.getUserId());
            mediaEntity.setMediaType(1);   //图片
            for (String fileName : dynamicEntity.getFileList()) {
                mediaEntity.setFileName(fileName);
                list1.add(mediaEntity);
            }
            boolean result = mediaService.saveBatch(list1);
            if (!result) {
                throw new ExceptionDto(ConstConfig.RE_ERROR_CODE, ConstConfig.RE_ERROR_MESSAGE);
            }
        }
        return new APIResponse();
    }

}
