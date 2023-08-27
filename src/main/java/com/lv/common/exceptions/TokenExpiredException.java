package com.lv.common.exceptions;

import com.lv.common.constants.ResponseCode;

/**
 * @author QiangZai
 * @version 1.0
 * @date 2023/8/26 12:35 AM
 */
public class TokenExpiredException extends BaseException{

    public TokenExpiredException(Throwable cause) {
        super(cause);
        this.message = "您的登陆状态已过期";
    }
}
