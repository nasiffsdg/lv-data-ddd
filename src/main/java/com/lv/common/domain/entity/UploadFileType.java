package com.lv.common.domain.entity;

import cn.hutool.core.util.StrUtil;

/**
 * 支持的文件上传类型
 * @author QiangZai
 * @version 1.0
 * @date 2023/7/30 5:48 PM
 */
public class UploadFileType {

    public static final String JAR = "jar";

    /**
     * 判断是否支持该文件类型
     * @param type 文件类型
     * @return 是否支持
     */
    public static boolean supportOrNo(String type){
        return StrUtil.equals(type, JAR);
    }
}
