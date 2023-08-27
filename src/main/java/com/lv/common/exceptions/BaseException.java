package com.lv.common.exceptions;

/**
 * 业务异常处理基类
 * @author QiangZai
 * @version 1.0
 * @date 2023/8/26 12:37 AM
 */
public class BaseException extends RuntimeException{

    // 业务异常信息
    protected String message;
    // 业务异常码
    protected Integer code;
    // 异常原因
    protected Throwable cause;
    public BaseException(Throwable cause) {
        this.cause = cause;
    }
    public Integer getCode() {
        return code;
    }
    @Override
    public Throwable getCause() {
        return cause;
    }
    @Override
    public String getMessage() {
        return message;
    }
}
