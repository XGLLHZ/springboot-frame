package org.huangzi.main.common.controller;

import org.huangzi.main.common.utils.APIResponse;
import org.huangzi.main.common.annotation.LogAnnotation;
import org.huangzi.main.common.entity.LogEntity;
import org.huangzi.main.common.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * @author: XGLLHZ
 * @date: 2020/2/6 下午6:52
 * @description: 系统日志前端控制器
 */
@RestController
@RequestMapping("/admin/log")
public class LogController {

    @Autowired
    LogService logService;

    @LogAnnotation("日志列表")
    @RequestMapping("/list")
    public APIResponse getList(@RequestBody LogEntity logEntity) {
        return logService.getList(logEntity);
    }

    @LogAnnotation("日志详情")
    @RequestMapping("/get")
    public APIResponse getLog(@RequestBody LogEntity logEntity) {
        return logService.getLog(logEntity);
    }

    @LogAnnotation("导出日志")
    @RequestMapping("/export")
    public void exportLog(@RequestBody LogEntity logEntity, HttpServletResponse response) {
        logEntity.setCurrentPage(-1);
        logEntity.setPageSize(-1);
        logService.exportLog(logEntity, response);
    }

    @LogAnnotation("导入日志")
    @RequestMapping("/import")
    public APIResponse importLog(@RequestParam("file") MultipartFile file) {
        return logService.importLog(file);
    }

}
