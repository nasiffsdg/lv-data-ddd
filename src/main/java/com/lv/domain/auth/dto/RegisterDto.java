package com.lv.domain.auth.dto;

import lombok.Data;
import lombok.ToString;

/**
 * 用户注册数据
 * @author QiangZai
 * @version 1.0
 * @date 2023/8/28 10:47 PM
 */
@Data
@ToString
public class RegisterDto {
    private String userName;
    private String mail;
    private String userPwd;
    private String code;
}
