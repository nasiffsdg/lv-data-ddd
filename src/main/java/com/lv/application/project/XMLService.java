package com.lv.application.project;

import java.io.File;
import java.io.InputStream;
import java.util.jar.JarEntry;

/**
 * xml 文件处理接口
 * @author QiangZai
 * @version 1.0
 * @date 2023/7/31 9:01 PM
 */
public interface XMLService {


    /**
     * xml 文件解析
     * @param entry xml文件
     */
    void resolve(InputStream inputStream, JarEntry entry);
}
