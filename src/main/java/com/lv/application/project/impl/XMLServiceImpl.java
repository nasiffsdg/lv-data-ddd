package com.lv.application.project.impl;

import com.lv.application.project.XMLService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.InputStream;
import java.util.jar.JarEntry;

/**
 * @author QiangZai
 * @version 1.0
 * @date 2023/7/31 9:04 PM
 */
@Log4j2
@Service
public class XMLServiceImpl implements XMLService {

    /**
     * TODO xml 文件解析
     * @param entry xml文件
     */
    @Override
    public void resolve(InputStream inputStream, JarEntry entry) {
       log.info("xml文件解析" + entry.getName());
    }
}
