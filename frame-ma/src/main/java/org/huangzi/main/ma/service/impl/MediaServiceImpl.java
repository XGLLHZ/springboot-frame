package org.huangzi.main.ma.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.huangzi.main.common.utils.APIResponse;
import org.huangzi.main.ma.entity.MediaEntity;
import org.huangzi.main.ma.mapper.MediaMapper;
import org.huangzi.main.ma.service.MediaService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

/**
 * @author: XGLLHZ
 * @date: 2020/4/13 下午5:03
 * @description: 媒体事务层实现类
 */
@Primary
@Service("MediaServiceImpl")
public class MediaServiceImpl extends ServiceImpl<MediaMapper, MediaEntity> implements MediaService {

    @Override
    public APIResponse getList(MediaEntity mediaEntity) {
        return null;
    }

    @Override
    public APIResponse insertMedia(MediaEntity mediaEntity) {
        return null;
    }

    @Override
    public APIResponse deleteMedia(MediaEntity mediaEntity) {
        return null;
    }

}
