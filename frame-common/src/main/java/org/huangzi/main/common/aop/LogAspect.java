package org.huangzi.main.common.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.huangzi.main.common.utils.ConstConfig;
import org.huangzi.main.common.utils.ThrowableUtil;
import org.huangzi.main.common.entity.LogEntity;
import org.huangzi.main.common.service.LogService;
import org.springframework.stereotype.Component;

/**
 * @author: XGLLHZ
 * @date: 2020/2/6 下午6:52
 * @description: aop实现日志切点拦截 切面 = 切点 + 通知
 */
@Slf4j
@Aspect
@Component
public class LogAspect {

    //spring 4 之后推荐构造方法式的注入
    private final LogService logService;

    public LogAspect(LogService logService) {
        this.logService = logService;
    }

    private ThreadLocal<Long> threadLocal = new ThreadLocal<>();

    /**
     * 配置切入点
     */
    @Pointcut("@annotation(org.huangzi.main.common.annotation.LogAnnotation)")
    public void logPointCut() {
        //让同类中其他方法使用此切入点
    }

    /**
     * 配置通知（环绕通知，即方法执行完后）
     * 正常通知
     * @param joinPoint
     */
    @Around("logPointCut()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Object object;
        //请求耗时
        threadLocal.set(System.currentTimeMillis());
        LogEntity logEntity = new LogEntity();
        logEntity.setLogType(ConstConfig.ORDINARY_LOG_CODE);
        object = joinPoint.proceed();   //执行方法，object 为方法返回值
        logEntity.setRequestTime(System.currentTimeMillis() - threadLocal.get());
        logEntity.setExceptionDetail("");
        threadLocal.remove();
        logService.insertLog(joinPoint, logEntity);
        return object;
    }

    /**
     * 配置通知
     * 异常通知
     * @param joinPoint
     * @param throwable
     */
    @AfterThrowing(pointcut = "logPointCut()", throwing = "throwable")
    public void logException(JoinPoint joinPoint, Throwable throwable) {
        //请求耗时
        threadLocal.set(System.currentTimeMillis());
        LogEntity logEntity = new LogEntity();
        logEntity.setLogType(ConstConfig.ERROR_LOG_CODE);
        logEntity.setRequestTime(System.currentTimeMillis() - threadLocal.get());
        threadLocal.remove();
        //异常详情
        logEntity.setExceptionDetail(ThrowableUtil.getStackTrace(throwable));
        logService.insertLog((ProceedingJoinPoint) joinPoint, logEntity);
    }

}
