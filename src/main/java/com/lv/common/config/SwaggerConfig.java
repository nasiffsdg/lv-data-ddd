package com.lv.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger配置
 * @author QiangZai
 * @version 1.0
 * @date 2023/8/5 11:27 PM
 */
@EnableSwagger2
@Configuration
public class SwaggerConfig {

    @Bean
    public Docket createDocket(){
        return new Docket(DocumentationType.SWAGGER_2)
                .pathMapping("/")
                .enable(true)
                .host("localhost:9111")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.lv.interfaces"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    @Bean
    public ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("swagger测试文档")
                .description("swagger测试的API文档-swagger2")
                .contact(new Contact("强仔懒得动","http://localhost:9111/swagger/doc.html","18804024672@163.com"))
                .termsOfServiceUrl("http://localhost:9111/swagger/doc.html")
                .version("1.0.0")
                .build();
    }
}
