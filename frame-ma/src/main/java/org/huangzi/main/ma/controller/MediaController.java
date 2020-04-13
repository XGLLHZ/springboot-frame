package org.huangzi.main.ma.controller;

import org.huangzi.main.common.annotation.LogAnnotation;
import org.huangzi.main.common.utils.APIResponse;
import org.huangzi.main.ma.entity.MediaEntity;
import org.huangzi.main.ma.service.MediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: XGLLHZ
 * @date: 2020/4/13 下午10:08
 * @description: 媒体前端控制器
 */
@RestController
@RequestMapping("/ma/media")
public class MediaController {

    @Autowired
    private MediaService mediaService;

    @LogAnnotation("媒体列表-分页")
    @RequestMapping("/list")
    public APIResponse getList(@RequestBody MediaEntity mediaEntity) {
        return mediaService.getList(mediaEntity);
    }

    @LogAnnotation("新增媒体")
    @RequestMapping("/insert")
    public APIResponse insertMedia(@RequestBody MediaEntity mediaEntity) {
        return mediaService.insertMedia(mediaEntity);
    }

    @LogAnnotation("删除媒体")
    @RequestMapping("/delete")
    public APIResponse deleteMedia(@RequestBody MediaEntity mediaEntity) {
        return mediaService.deleteMedia(mediaEntity);
    }

}
