package com.lv.common.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

/**
 * @author QiangZai
 * @version 1.0
 * @date 2023/7/8 10:07 PM
 */
@Service
public class CacheManagerService {

    private final CacheManager cacheManager;
    @Autowired
    public CacheManagerService(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    @SuppressWarnings({ "unchecked" })
    public <T> T getCache(String cacheName, String key) {
        Cache cache = cacheManager.getCache(cacheName);
        if (cache == null) {
            return null;
        }
        Cache.ValueWrapper valueWrapper = cache.get(key);
        if (valueWrapper == null) {
            return null;
        }
        return (T) valueWrapper.get();
    }
    public void putCache(String cacheName, String key, Object value) {
        Cache cache = cacheManager.getCache(cacheName);
        if (cache != null) {
            cache.put(key, value);
        }
    }
    public void evictCache(String cacheName, String key) {
        Cache cache = cacheManager.getCache(cacheName);
        if (cache != null) {
            cache.evict(key);
        }
    }
}
