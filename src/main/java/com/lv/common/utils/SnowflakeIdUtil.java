package com.lv.common.utils;

import cn.hutool.core.lang.Snowflake;

/**
 * 雪花算法
 * @author QiangZai
 * @version 1.0
 * @date 2023/8/28 11:32 PM
 */
public class SnowflakeIdUtil {

    /**
     * 字符串id
     * @return 分布式唯一id
     */
    public static String nextIdStr(){
        return new Snowflake().nextIdStr();
    }

    /**
     *  @return 分布式唯一id
     */
    public static Long nextIdLong(){
        return new Snowflake().nextId();
    }
}
