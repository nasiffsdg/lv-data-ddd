package com.lv.application.project;

import java.io.InputStream;
import java.util.jar.JarEntry;

/**
 * 属性文件解析
 * @author QiangZai
 * @version 1.0
 * @date 2023/7/31 9:09 PM
 */
public interface PropertiesService {

    /**
     * 属性文件解析
     * @param entry 属性文件
     */
    void resolve(InputStream inputStream, JarEntry entry);
}
