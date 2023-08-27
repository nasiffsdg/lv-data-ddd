package com.lv.common.config;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

/**
 * @author QiangZai
 * @version 1.0
 * @date 2023/7/15 5:13 PM
 */
@Configuration
public class CaffeineConfig {

    Logger logger = LoggerFactory.getLogger(CaffeineConfig.class);

    @Bean
    public Cache<String, Object> cache() {
        // 创建本地Caffeine缓存
        final Cache<String, Object> cache = Caffeine.newBuilder()
                // 最后一次写入或最后一次更新后的过期时间
                .expireAfterWrite(30, TimeUnit.SECONDS)
                // 初始的缓存空间大小
                .initialCapacity(10)
                // 缓存的最大条数
                .maximumSize(1024)
                //记录下缓存的一些统计数据，例如命中率等
                .recordStats()
                .weakKeys()
                .weakValues()
                .build();
        logger.info("本地缓存Caffeine初始化完成 ...");
        return cache;
    }
}
