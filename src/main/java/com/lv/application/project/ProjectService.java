package com.lv.application.project;

import org.springframework.web.multipart.MultipartFile;

/**
 * 项目文件解析接口
 * @author QiangZai
 * @version 1.0
 * @date 2023/7/30 5:36 PM
 */
public interface ProjectService {

    /**
     * 文件解析并上传到Oss文件存储
     * @param file 文件实体
     */
    void resolveAndUploadProject(MultipartFile file);
}
