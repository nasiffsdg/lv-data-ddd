package com.lv.common.config.mybatis;

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
