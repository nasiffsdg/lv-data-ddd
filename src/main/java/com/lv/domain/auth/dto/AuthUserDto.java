package com.lv.domain.auth.dto;

import com.lv.domain.auth.entity.AuthUserDo;
import lombok.Data;

/**
 * @author QiangZai
 * @version 1.0
 * @date 2023/7/14 11:34 PM
 */
@Data
public class AuthUserDto {
    /**
     * 用户信息
     */
    private AuthUserDo authUser;
    /**
     * 授权token
     */
    private String authToken;
}
