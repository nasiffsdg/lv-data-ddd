package com.lv.common.config.interceptor;

import com.lv.common.interceptor.JwtInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * 拦截器配置无需认证的请求
 * @author QiangZai
 * @version 1.0
 * @date 2023/7/14 10:42 PM
 */
@Configuration
public class InterceptorConfig extends WebMvcConfigurationSupport {

    /**
     * 注册拦截器
     */
    @Bean
    public JwtInterceptor getJwtInterceptor(){
        return new JwtInterceptor();
    }


    @Override
    protected void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(getJwtInterceptor())
                .excludePathPatterns("/auth/user/**")
                .addPathPatterns("/**");   //其他接口保护 token验证
    }
}
