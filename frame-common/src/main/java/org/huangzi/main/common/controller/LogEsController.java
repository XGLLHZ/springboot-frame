package org.huangzi.main.common.controller;

import org.huangzi.main.common.annotation.LogAnnotation;
import org.huangzi.main.common.entity.LogEntity;
import org.huangzi.main.common.service.LogEsService;
import org.huangzi.main.common.utils.APIResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: XGLLHZ
 * @date: 2020/7/20 下午3:16
 * @description: 日志 es 前端控制器
 */
@RestController
@RequestMapping("/es/log")
public class LogEsController {

    @Autowired
    private LogEsService logEsService;

    @LogAnnotation("es 导入系统日志数据")
    @RequestMapping("/import")
    public APIResponse importLog(@RequestBody LogEntity logEntity) {
        return logEsService.importLog(logEntity);
    }

    @LogAnnotation("es 查询系统日志")
    @RequestMapping("/getList")
    public APIResponse getList(@RequestBody LogEntity logEntity) {
        return logEsService.getList(logEntity);
    }

}
