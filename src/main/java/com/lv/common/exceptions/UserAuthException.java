package com.lv.common.exceptions;

/**
 * 用户权限异常
 * @author QiangZai
 * @version 1.0
 * @date 2023/8/27 6:27 PM
 */
public class UserAuthException extends BaseException{

    // 用户名密码错误
    public static final Integer LOGIN_FALSE =  600001;
    // 验证码发送异常
    public static final Integer EMAIL_SEND_FALSE =  600002;
    // 邮箱格式异常
    public static final Integer EMAIL_FALSE =  600003;
    // 注册参数有误
    public static final Integer REGISTER_MESSAGE_FALSE =  600003;
    // 验证码有误
    public static final Integer REGISTER_CODE_FALSE =  600004;

    public UserAuthException(Throwable cause, Integer code, String message) {
        super(cause, code, message);
    }
    public UserAuthException(Integer code, String message) {
        super(null, code, message);
    }
}
