package com.lv.common.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * @author QiangZai
 * @version 1.0
 * @date 2023/7/15 11:45 PM
 */
public class CrossInterceptor implements HandlerInterceptor {
    // 访问前进行拦截
    @Override
    public boolean preHandle(HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object o) {
        String originHeads = request.getHeader("Origin");
        response.setHeader("Access-Control-Allow-Origin", originHeads);
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Headers",
                "Content-Type,Content-Length, Authorization, Accept,X-Requested-With");
        response.setHeader("Access-Control-Allow-Methods", "PUT,POST,GET,DELETE,OPTIONS");
        response.setHeader("X-Powered-By", "Jetty");
        return true;
    }
}
