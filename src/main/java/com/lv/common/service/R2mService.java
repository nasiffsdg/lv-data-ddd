package com.lv.common.service;

import com.lv.common.constants.CacheConstant;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author QiangZai
 * @version 1.0
 * @date 2023/7/8 10:09 PM
 */
@Service
public class R2mService {
    private static final Logger logger = LoggerFactory.getLogger(R2mService.class);

    @Resource
    private RedisTemplate<String, Object> redisTemplate;
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 指定缓存失效时间
     * @param key 键
     * @param time 时间(秒)
     * @return 是否成功
     */
    public Boolean expire(String key, long time) {
        if (key.contains(" ") ){
            logger.error("缓存key不能包含空格");
        }
        try {
            if (time > 0) {
                redisTemplate.expire(key, time, TimeUnit.SECONDS);
            }
            return Boolean.TRUE;
        }
        catch (Exception e) {
            logger.error("Set expire error: {}", e.getMessage());
            return Boolean.FALSE;
        }
    }
    /**
     * 根据key 获取过期时间
     * @param key 键 不能为null
     * @return 时间(秒) 返回-1代表为永久有效 失效时间为0，说明该主键未设置失效时间（失效时间默认为-1）
     */
    public Long getExpire(String key) {

        if (key.contains(" ") ){
            logger.error("缓存key不能包含空格");
        }
        return redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }
    /**
     * 判断key是否存在
     * @param key 键
     * @return true 存在 false 不存在
     */
    public Boolean hasKey(String key) {

        if (key.contains(" ") ){
            logger.error("缓存key不能包含空格");
        }
        try {
            return redisTemplate.hasKey(key);
        }
        catch (Exception e) {
            logger.error("Error getting hasKey: {}", e.getMessage());
            return Boolean.FALSE;
        }
    }

    /**
     * 删除缓存
     * @param key 可以传一个值 或多个
     */
    public void del(String... key) {

        if (key != null && key.length > 0) {
            for (String s : key) {
                logger.error("缓存key不能包含空格");
            }
            try {
                if (key.length == 1) {
                    redisTemplate.delete(key[0]);
                }
                else {
                    redisTemplate.delete(Arrays.asList(key));
                }
            }catch (Exception e){
                logger.error("Error del hasKey: {}", e.getMessage());
            }
        }
    }
    /**
     * 普通缓存获取
     * @param key 键
     * @return 值
     */
    public Object get(String key) {
        if (key.contains(" ") ){
            logger.error("缓存key不能包含空格");
        }
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * 普通缓存放入并设置时间
     *
     * @param key   键
     * @param value 值
     * @param time  时间(秒) time要大于0 如果time小于等于0 将设置无限期
     */
    public void set(String key, Object value, long time) {
        if (key.contains(" ")) {
            logger.error("缓存key不能包含空格");
        }
        try {
            if (time > 0) {
                redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
            }
            else {
                redisTemplate.opsForValue().set(key, value);
            }
        }
        catch (Exception e) {
            logger.error("Redis opsForValue error: {}", e.getMessage());
        }
    }

    /**
     * 递增 此时value值必须为int类型 否则报错
     * @param key 键
     * @param delta 要增加几(大于0)
     * @return 自增后的值
     */
    public Long incr(String key, long delta) {
        if (key.contains(" ")) {
            logger.error("缓存key不能包含空格");
        }
        if (delta < 0) {
            throw new RuntimeException("递增因子必须大于0");
        }
        return redisTemplate.opsForValue().increment(key, delta);
    }

    /**
     * 递减
     * @param key 键
     * @param delta 要减少几(小于0)
     * @return 自减后的值
     */
    public Long decr(String key, long delta) {
        if (key.contains(" ")) {
            logger.error("缓存key不能包含空格");
        }
        if (delta < 0) {
            throw new RuntimeException("递减因子必须小于0");
        }
        return redisTemplate.opsForValue().increment(key, -delta);
    }

    public boolean setLongValue(String key, Long value, long time) {
        if (key.contains(" ")) {
            logger.error("缓存key不能包含空格");
        }
        try {
            if (time > 0) {
                stringRedisTemplate.opsForValue().set(key, String.valueOf(value), time, TimeUnit.SECONDS);
            }
            else {
                stringRedisTemplate.opsForValue().set(key, String.valueOf(value));
            }
            return true;
        }
        catch (Exception e) {
            logger.error("setLongValue() error: {}", e.getMessage());
            return false;
        }
    }

    /**
     * 普通缓存获取
     * @param key 键
     * @return 值
     */
    public Long getLongValue(String key) {
        if (key == null) {
            return null;
        }
        if (key.contains(" ")) {
            logger.error("缓存key不能包含空格");
        }
        String result = stringRedisTemplate.opsForValue().get(key);
        if (result == null) {
            return null;
        }
        return Long.valueOf(result);
    }

    /**
     * list存储
     * @param key 缓存key
     * @param val 缓存值
     */
    public void putList(String key, String val){
        stringRedisTemplate.opsForList().leftPush(key, val);
    }

    /**
     * set存储
     * @param key 缓存key
     * @param val 缓存值
     */
    public void putSet(String key, String val){
        stringRedisTemplate.opsForSet().add(key, val);
    }

    /**
     * set获取
     * @param key 缓存key
     * @param val 缓存值
     */
    public void setContainVal(String key, String val){
        stringRedisTemplate.opsForSet().isMember(key, val);
    }

    /**
     * map存储
     * @param key 缓存key
     * @param key2 hash key
     * @param val 值
     */
    public void putMap(String key, String key2, String val){
        stringRedisTemplate.opsForHash().put(key, key2, val);
    }

    /**
     * map获取
     * @param key 缓存key
     * @param key1 hash key
     * @return 值
     */
    public Object getFromMap(String key, String key1){
        return stringRedisTemplate.opsForHash().get(key, key1);
    }

    /**
     * map是否存在
     * @param key 缓存key
     * @param key1 hashKey
     * @return 是否存在
     */
    public Boolean containForMap(String key, String key1){
        return stringRedisTemplate.opsForHash().hasKey(key, key1);
    }



    /**
     * 批量删除缓存
     * @param keys 键
     */
    public void deleteBatch(List<String> keys) {
        if (CollectionUtils.isEmpty(keys)) {
            return;
        }
        for (String key : keys) {
            if (key.contains(" ")) {
                logger.error("缓存key不能包含空格");
            }
        }
        redisTemplate.delete(keys);
    }

    /**
     * 批量删除缓存
     * @param cacheName 缓存名
     * @param cacheKeys 缓存key
     */
    public void deleteBatch(String cacheName, List<?> cacheKeys) {
        if (StringUtils.isEmpty(cacheName) || CollectionUtils.isEmpty(cacheKeys)) {
            return;
        }
        List<String> strCacheKeys = cacheKeys.stream().map(String::valueOf).toList();
        List<String> keys = new ArrayList<>();
        for (String cacheKey : strCacheKeys) {
            String key = cacheName + CacheConstant.UNION + cacheKey;
            keys.add(key);
            if (key.contains(" ")){
                logger.error("缓存key不能包含空格");
            }
        }
        redisTemplate.delete(keys);
    }

    /**
     * 比较和删除标记，原子性
     * @return 是否成功
     */
    public boolean cad(String key, String value) {

        if (key.contains(" ")) {
            logger.error("缓存key不能包含空格");
        }

        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";

        //通过lure脚本原子验证令牌和删除令牌
        Long result = stringRedisTemplate.execute(new DefaultRedisScript<>(script, Long.class),
                Collections.singletonList(key),
                value);
        return !Objects.equals(result, 0L);
    }
}
