package org.huangzi.main.common.aop;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.huangzi.main.common.dto.ExceptionDto;
import org.huangzi.main.common.utils.ConstConfig;
import org.huangzi.main.common.utils.TokenUtil;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author: XGLLHZ
 * @date: 2020/6/17 下午10:39
 * @description:
 */
@Slf4j
@Aspect
@Component
public class TokenAspect {

    /**
     * 配置切入点
     */
    @Pointcut("@annotation(org.huangzi.main.common.annotation.CheckToken)")
    public void tokenPointCut() {
        //切入点
    }

    /**
     * 配置通知
     * @param joinPoint
     * @return
     */
    @Before("tokenPointCut()")
    public Object tokenBefore(JoinPoint joinPoint) {
        Object object = joinPoint.getArgs();
        Object[] objects = joinPoint.getArgs();
        String token = (String) getFieldValueByName(objects[0]);
        if (StringUtils.isBlank(token)) {
            throw new ExceptionDto(ConstConfig.RE_TOKEN_INVALID_CODE, ConstConfig.RE_TOKEN_INVALID_MSG);
        }
        if (TokenUtil.checkToken(token) == 1002) {
            throw ExceptionDto.TOKEN_EXPIRE;
        }
        return object;
    }

    /**
     * 根据属性名获取属性值
     * @param o
     * @return
     */
    private static Object getFieldValueByName(Object o) {
         try {
              String firstLetter = "token".substring(0, 1).toUpperCase();
              String getter = "get" + firstLetter + "token".substring(1);
              Method method = o.getClass().getMethod(getter);
             return method.invoke(o);
         } catch (Exception e) {
              log.error(e.getMessage(), e);
              return null;
         }
    }

}
