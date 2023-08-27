package com.lv.domain.auth.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * 用户权限表
 * @author lvxueqiang
 * @TableName lv_auth_power
 */
@Data
@ToString
public class AuthPowerDo implements Serializable {
    /**
     * 主键id
     */
    private Long id;

    /**
     * 权限号
     */
    private String powerNo;

    /**
     * 权限名
     */
    private String powerName;

    /**
     * 前端路径
     */
    private String frontUrl;

    /**
     * 后端路径
     */
    private String beheadUrl;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 
     */
    private Integer isDeleted;

    @Serial
    private static final long serialVersionUID = 1L;
}