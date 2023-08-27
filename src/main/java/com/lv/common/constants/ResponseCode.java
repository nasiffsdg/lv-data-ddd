package com.lv.common.constants;

/**
 * 响应状态码
 * @author QiangZai
 * @version 1.0
 * @date 2023/7/14 1:12 AM
 */
public interface ResponseCode {

    /**
     * 响应成功
     */
    Integer SUCCESS = 200;
    /**
     * 默认响应失败
     */
    Integer ERROR = 500;
    /**
     * 未授权
     */
    Integer UNAUTHORIZED = 401;
    /**
     * 无权限
     */
    Integer FORBIDDEN = 403;
}
