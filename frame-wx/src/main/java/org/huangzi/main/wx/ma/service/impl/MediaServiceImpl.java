package org.huangzi.main.wx.ma.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.huangzi.main.common.utils.APIResponse;
import org.huangzi.main.common.utils.ConstConfig;
import org.huangzi.main.wx.ma.entity.MediaEntity;
import org.huangzi.main.wx.ma.mapper.MediaMapper;
import org.huangzi.main.wx.ma.service.MediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: XGLLHZ
 * @date: 2020/4/13 下午5:03
 * @description: 媒体事务层实现类
 */
@Primary
@Service("MediaServiceImpl")
public class MediaServiceImpl extends ServiceImpl<MediaMapper, MediaEntity> implements MediaService {

    @Autowired
    private MediaMapper mediaMapper;

    @Override
    public APIResponse getList(MediaEntity mediaEntity) {
        Page<MediaEntity> page = new Page<>(mediaEntity.getCurrentPage(), mediaEntity.getPageSize());
        List<MediaEntity> list = mediaMapper.getList(page, mediaEntity);
        Integer total = mediaMapper.getTotal(mediaEntity);
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
    public APIResponse insertMedia(MediaEntity mediaEntity) {
        List<MediaEntity> list = new ArrayList<>();
        for (String fileName : mediaEntity.getFileList()) {
            MediaEntity mediaEntity1 = new MediaEntity();
            mediaEntity1.setUserId(mediaEntity.getUserId());
            mediaEntity1.setObjectType(2);
            mediaEntity1.setMediaType(1);
            mediaEntity1.setFileName(fileName);
            list.add(mediaEntity1);
        }
        boolean result = saveBatch(list);
        if (!result) {
            return new APIResponse(ConstConfig.RE_ERROR_CODE, ConstConfig.RE_ERROR_MESSAGE);
        }
        return new APIResponse();
    }

    @Override
    public APIResponse deleteMedia(MediaEntity mediaEntity) {
        List<MediaEntity> list = mediaMapper.selectBatchIds(mediaEntity.getIds());
        List<MediaEntity> list1 = new ArrayList<>();
        for (MediaEntity mediaEntity1 : list) {
            if (mediaEntity1.getUserId().equals(mediaEntity.getUserId())) {
                mediaEntity1.setDeleteFlag(ConstConfig.DELETE_FLAG_ONE);
                list1.add(mediaEntity1);
            }
        }
        if (list1.size() == 0) {
            return new APIResponse(ConstConfig.RE_CANNOT_DEL_CODE, ConstConfig.RE_CANNOT_DEL_MESSAGE);
        }
        boolean result = updateBatchById(list1);
        if (!result) {
            return new APIResponse(ConstConfig.RE_ERROR_CODE, ConstConfig.RE_ERROR_MESSAGE);
        }
        return new APIResponse();
    }

}
