package org.huangzi.main.common.config;

import org.huangzi.main.common.dto.ExceptionDto;
import org.huangzi.main.common.utils.APIResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author: XGLLHZ
 * @date: 2020/4/10 下午2:37
 * @description: 异常处理
 */
@ControllerAdvice
public class ExceptionConfig {

    @ResponseBody
    @ExceptionHandler(ExceptionDto.class)
    public APIResponse handleException(ExceptionDto exceptionEntity) {
        return new APIResponse(exceptionEntity.getCode(), exceptionEntity.getMessage());
    }

}
