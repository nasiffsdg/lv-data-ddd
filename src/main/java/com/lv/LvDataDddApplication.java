package com.lv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 数据服务启动基类
 * @author lvxueqiang
 */
@EnableDiscoveryClient
@SpringBootApplication
public class LvDataDddApplication {

    public static void main(String[] args) {

        /*
         * 指定使用的日志框架，否则将会告警
         * RocketMQLog:WARN No appenders could be found for logger (io.netty.util.internal.InternalThreadLocalMap).
         * RocketMQLog:WARN Please initialize the logger system properly.
         */
        System.setProperty("rocketmq.client.logUseSlf4j", "true");
        SpringApplication.run(LvDataDddApplication.class, args);
    }

}
