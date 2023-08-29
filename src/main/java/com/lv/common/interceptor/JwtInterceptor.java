package com.lv.common.interceptor;

import cn.hutool.core.util.StrUtil;
import com.lv.common.annotation.IgnoreToken;
import com.lv.common.constants.ContentTypeConstant;
import com.lv.common.constants.ResponseCode;
import com.lv.common.domain.entity.ResponseInfo;
import com.lv.common.utils.JWTUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import java.lang.reflect.Method;


/**
 * Token统一拦截器代码
 *
 * @author QiangZai
 * @version 1.0
 * @date 2023/7/14 10:42 PM
 */
@Log4j2
public class JwtInterceptor implements HandlerInterceptor {

    private static final String AUTH = "Authorization";
    private static final String AUTH_USER_NAME = "AuthUserName";

    @Override
    public boolean preHandle(@NonNull HttpServletRequest request, HttpServletResponse response, @NonNull Object handler) {

        log.info("===================您的请求被拦截鉴权===================");
//        // 设置响应体类型
//        response.setContentType(ContentTypeConstant.JSON);
//        //如果接口或者类上有@IgnoreToken注解，意思该接口不需要token就能访问，需要放行
//        HandlerMethod handlerMethod = (HandlerMethod) handler;
//        Method method = handlerMethod.getMethod();
//        //先从类上获取该注解，判断类上是否加了IgnoreToken ，代表不需要token，直接放行
//        IgnoreToken annotation = handlerMethod.getBeanType().getAnnotation(IgnoreToken.class);
//        if (annotation == null) {
//            //再从方法上获取该注解
//            if (method.isAnnotationPresent(IgnoreToken.class)) {
//                annotation = method.getAnnotation(IgnoreToken.class);
//                log.info("请求方法 {} 上有注解 {} ", method.getName(), annotation);
//            }
//        }
//        if (annotation != null) {
//            return true;
//        }
//        // 验证
//        String token = request.getParameter(AUTH);
//        String username = request.getParameter(AUTH_USER_NAME);
//        if (StrUtil.isEmpty(token)) {
//            log.error("Authorization不允许为空，请重新登录");
//            response.setStatus(ResponseCode.UNAUTHORIZED);
//            return false;
//        }
//        if (StrUtil.isEmpty(username)) {
//            log.error("用户名不允许为空，请重新登录");
//            response.setStatus(ResponseCode.UNAUTHORIZED);
//            return false;
//        }
//        ResponseInfo responseInfo = JWTUtil.verifyToken(token, username);
//        if (!responseInfo.getCode().equals(ResponseCode.SUCCESS)) {
//            log.error("token valid error!");
//            response.setStatus(ResponseCode.UNAUTHORIZED);
//            return false;
//        }
        log.info("===================您的请求被放行===================");
        return true;
    }
}
