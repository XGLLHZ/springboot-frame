package org.huangzi.main.common.dto;

import org.huangzi.main.common.utils.ConstConfig;

/**
 * @author: XGLLHZ
 * @date: 2020/4/10 下午2:35
 * @description: 异常实体类
 */
public class ExceptionDto extends RuntimeException {

    private static final long serialVersionUID = -4368873598973127606L;

    private final Integer code;

    private final Object data;

    public ExceptionDto(Integer code, String message) {
        super(message);
        this.code = code;
        this.data = null;
    }

    public ExceptionDto(Integer code, String message, Object data, Exception ex) {
        super(message, ex);
        this.code = code;
        this.data = data;
    }

    public ExceptionDto(Integer code, String message, Exception ex) {
        super(message, ex);
        this.code = code;
        this.data = null;
    }

    public Throwable getInnerException() {
        return super.getCause();
    }

    public Object getData() {
        return data;
    }

    public Integer getCode() {
        return code;
    }

    public static final ExceptionDto TOKEN_EXPIRE = new ExceptionDto(ConstConfig.RE_LOGIN_EXPIRE_CODE, ConstConfig.RE_LOGIN_EXPIRE_MSG);

}
