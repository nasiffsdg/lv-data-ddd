package com.lv.domain.auth.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * 用户角色表
 * @author lvxueqiang
 * @TableName lv_auth_role
 */
@Data
@ToString
public class AuthRoleDo implements Serializable {
    /**
     * 主键id
     */
    private Long id;

    /**
     * 角色号
     */
    private String roleNo;

    /**
     * 角色名
     */
    private String roleName;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 逻辑删除
     */
    private Integer isDeleted;

    @Serial
    private static final long serialVersionUID = 1L;
}