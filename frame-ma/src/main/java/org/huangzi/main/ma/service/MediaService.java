package org.huangzi.main.ma.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.huangzi.main.common.utils.APIResponse;
import org.huangzi.main.ma.entity.MediaEntity;

/**
 * @author: XGLLHZ
 * @date: 2020/4/13 下午5:00
 * @description: 媒体事务层接口
 */
public interface MediaService extends IService<MediaEntity> {

    /**
     * 列表-分页
     * @param mediaEntity
     * @return
     */
    APIResponse getList(MediaEntity mediaEntity);

    /**
     * 新增
     * @param mediaEntity
     * @return
     */
    APIResponse insertMedia(MediaEntity mediaEntity);

    /**
     * 删除
     * @param mediaEntity
     * @return
     */
    APIResponse deleteMedia(MediaEntity mediaEntity);

}
