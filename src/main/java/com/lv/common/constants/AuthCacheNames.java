package com.lv.common.constants;
/**
 * @author QiangZai
 * @version 1.0
 * @date 2023/7/9 1:22 PM
 */
public class AuthCacheNames {

    /**
     * 前缀
     */
    private static final String AUTH_PREFIX = "auth::";

    /**
     * 用户信息缓存key
     */
    public static final String USER_KEY = AUTH_PREFIX+"user";
    /**
     * 用户验证码缓存key
     */
    public static final String CODE_KEY = AUTH_PREFIX+"code";

}
