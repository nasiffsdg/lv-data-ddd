package com.lv.application.project.impl;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.lang.JarClassLoader;
import cn.hutool.core.util.StrUtil;
import com.lv.application.project.ClassService;
import com.lv.application.project.ProjectService;
import com.lv.application.project.PropertiesService;
import com.lv.application.project.XMLService;
import com.lv.common.constants.ResolveFileTypeConstant;
import com.lv.domain.project.ProjectInfoDo;
import jakarta.annotation.Resource;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * @author QiangZai
 * @version 1.0
 * @date 2023/7/30 5:42 PM
 */
@Log4j2
@Service
public class ProjectServiceImpl implements ProjectService {

    /**
     * 文件临时处理缓冲区
     */
    @Value("${file.tempPath}")
    private String tempFilePath;

    /**
     * 类文件解析处理接口
     */
    @Resource
    private ClassService classService;
    @Resource
    private XMLService xmlService;
    @Resource
    private PropertiesService propertiesService;

    @Override
    public void resolveAndUploadProject(MultipartFile file) {

        // 获取文件
        String filePath = tempFilePath + UUID.randomUUID() + StrUtil.DOT + Objects.requireNonNull(FileUtil.extName(file.getOriginalFilename()));
        File tempFile = new File(filePath);
        try {
            file.transferTo(tempFile);
        } catch (IOException e) {
            log.error("临时文件创建失败");
            throw new RuntimeException(e);
        }
        // 对jar包进行类加载
        JarClassLoader jarClassLoader = JarClassLoader.loadJar(new File(filePath));

        // jar包下文件解析
        jarResolve(filePath);

        // 通过卸载类加载器来对类进行卸载
        try {
            jarClassLoader.close();
            // 推荐进行一次gc
            System.gc();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // jar包上传到oss

        // 项目文件入库
        ProjectInfoDo insertProjectInfo = new ProjectInfoDo();
        insertProjectInfo.setName(FileUtil.extName(file.getOriginalFilename()));
        insertProjectInfo.setDescription("");
    }

    private void jarResolve(String filePath) {
        try (JarFile jarFile = new JarFile(new File(filePath));) {
            for (Enumeration<JarEntry> enums = jarFile.entries(); enums.hasMoreElements(); ) {
                JarEntry entry = enums.nextElement();
                // 如果市java文件
                if (entry.getName().endsWith(StrUtil.DOT + ResolveFileTypeConstant.JAVA)) {
                    // 通过反射进行类加载与解析
                    classService.resolve(entry.getName());
                } else if (entry.getName().endsWith(StrUtil.DOT + ResolveFileTypeConstant.XML)) {
                    // 对xml文件进行解析
                    xmlService.resolve(jarFile.getInputStream(entry), entry);
                } else if (entry.getName().endsWith(StrUtil.DOT + ResolveFileTypeConstant.PROPERTIES)) {
                    // 对属性文件进行解析
                    propertiesService.resolve(jarFile.getInputStream(entry), entry);
                }
            }
        } catch (IOException e) {
            log.error("jar包文件解析失败" + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}

