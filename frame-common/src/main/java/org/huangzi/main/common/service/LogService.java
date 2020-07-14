package org.huangzi.main.common.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.huangzi.main.common.utils.APIResponse;
import org.huangzi.main.common.entity.LogEntity;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * @author: XGLLHZ
 * @date: 2020/2/6 下午7:16
 * @description: 日志事务层接口
 */
public interface LogService extends IService<LogEntity> {

    /**
     * 新增日志
     * @param joinPoint
     * @return
     */
    Integer insertLog(ProceedingJoinPoint joinPoint, LogEntity logEntity);

    /**
     * 获取日志列表
     * @param logEntity logType：1：普通日志；2：异常日志
     * @return
     */
    APIResponse getList(LogEntity logEntity);

    /**
     * 详情
     * @param logEntity
     * @return
     */
    APIResponse getLog(LogEntity logEntity);

    /**
     * 导出
     * @param logEntity
     * @return
     */
    void exportLog(LogEntity logEntity, HttpServletResponse response);

    /**
     * 导入
     * @param file
     * @return
     */
    APIResponse importLog(MultipartFile file);

}
