package com.lv.interfaces.peoject;

import cn.hutool.core.io.FileUtil;
import com.lv.application.project.ProjectService;
import com.lv.common.domain.entity.ResponseInfo;
import com.lv.common.domain.entity.UploadFileType;
import jakarta.annotation.Resource;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;

/**
 * 项目处理加载接口
 * @author QiangZai
 * @version 1.0
 * @date 2023/7/30 4:59 PM
 */
@Log4j2
@RestController("mapper/project")
public class ProjectController {

    @Resource
    private ProjectService projectService;

    /**
     * 项目文件上传并解析
     * @return 响应体
     */
    @PostMapping("/projectUpload")
    public ResponseInfo loadProject(@RequestParam("file") MultipartFile file){

        // 文件判空
        if (file == null || file.isEmpty()){
            return ResponseInfo.fail("文件为空");
        }
        // 文件类型判断
        if (!UploadFileType.supportOrNo(Objects.requireNonNull(FileUtil.extName(file.getOriginalFilename())))){
            return ResponseInfo.fail("文件类型不支持");
        }

        try {
            projectService.resolveAndUploadProject(file);
        }catch (Exception e){
            log.error("文件上传解析错误"+e.getMessage());
            return ResponseInfo.fail("项目文件上传解析错误");
        }
        return ResponseInfo.success();
    }
}
