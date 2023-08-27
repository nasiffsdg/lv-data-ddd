package com.lv.application.project;

/**
 * 类文件处理
 * @author QiangZai
 * @version 1.0
 * @date 2023/7/31 8:58 PM
 */
public interface ClassService {

    /**
     * class文件解析
     * @param className 文件全类名
     */
    void resolve(String className);
}
