package org.huangzi.main.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author: XGLLHZ
 * @date: 2020/2/6 下午6:51
 * @description: 自定义日志注解
 */
@Target(ElementType.METHOD)   //日志作用范围（CLASS：类；METHOD：方法 等）
@Retention(RetentionPolicy.RUNTIME)   //日志有效范围（RUNTIME：运行时 等）
public @interface LogAnnotation {

    String value() default "";

}
