package com.lv.domain.auth.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 用户信息表
 * @author lvxueqiang
 * @TableName lv_auth_role_power
 */
@Data
@ToString
@EqualsAndHashCode
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
    private String eMail;

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
     * 逻辑删除
     */
    private Integer isDeleted;

}