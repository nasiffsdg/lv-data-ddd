package com.lv.domain.project;

import lombok.Data;
import lombok.ToString;

import java.io.Serial;
import java.util.Date;
import java.io.Serializable;

/**
 * 项目信息表(ProjectInfo)实体类
 * @author QiangZai
 * @version 1.0
 * @date 2023/8/6 1:28 AM
 */
@Data
@ToString
public class ProjectInfoDo implements Serializable {

    @Serial
    private static final long serialVersionUID = -32952343917199491L;
    /**
     * 主键id
     */
    private Long id;
    /**
     * 项目名称
     */
    private String name;
    /**
     * 项目所有人Id
     */
    private Long userId;
    /**
     * 项目描述
     */
    private String description;
    /**
     * 是否删除
     */
    private short isDeleted;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;
}

