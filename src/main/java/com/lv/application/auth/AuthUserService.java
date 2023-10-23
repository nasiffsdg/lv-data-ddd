package com.lv.application.auth;


import com.lv.domain.auth.dto.RegisterDto;
import com.lv.domain.auth.entity.AuthUserDo;

import java.util.List;

/**
* @author lvxueqiang
* @description 针对表【lv_auth_user(用户信息表)】的数据库操作Service
* @createDate 2023-08-27 16:32:40
*/
public interface AuthUserService {

    /**
     * 根据用户名或邮箱查询用户
     *
     * @param username 用户名
     * @return 用户列表
     */
    List<AuthUserDo> getUserByUsernameOrEmail(String username);

    /**
     * 获取登陆验证码并放入缓存中
     *
     * @param email 邮箱
     */
    void sendCode(String email);

    /**
     * 用户名信息注册
     *
     * @param registerDto 用户身份信息
     */
    void register(RegisterDto registerDto);

    /**
     * 更新用户密码信息
     * @param registerDto 用户信息
     */
    void updatePassword(RegisterDto registerDto);
}
