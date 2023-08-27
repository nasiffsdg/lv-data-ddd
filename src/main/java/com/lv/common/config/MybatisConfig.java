package com.lv.common.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * mybatis配置类
 * @author lvxueqiang
 */
@Configuration
@MapperScan({"com.lv.infrastructure.**.dao"})
public class MybatisConfig {

}
