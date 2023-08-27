package com.lv.domain.auth.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * 用户信息表
 * @author lvxueqiang
 * @TableName lv_auth_user
 */
@Data
@ToString
public class AuthUserDo implements Serializable {
    /**
     * 主键id
     */
    private Long id;

    /**
     * 用户号
     */
    private String userNo;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 用户密码
     */
    private String userPwd;

    /**
     * 用户邮箱
     */
    private String mall;

    /**
     * 性别｛1男/0女｝
     */
    private Integer gender;

    /**
     * 联系方式
     */
    private String phoneNum;

    /**
     * 用户头像
     */
    private String headPicture;

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