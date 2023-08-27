package com.lv.application.project.impl;

import com.lv.application.project.PropertiesService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.jar.JarEntry;

/**
 * @author QiangZai
 * @version 1.0
 * @date 2023/7/31 9:10 PM
 */
@Log4j2
@Service
public class PropertiesServiceImpl implements PropertiesService {
    @Override
    public void resolve(InputStream inputStream, JarEntry entry) {
        log.error("属性文件解析" + entry.getName());
    }
}
