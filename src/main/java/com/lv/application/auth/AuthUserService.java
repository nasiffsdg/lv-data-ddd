package com.lv.application.auth;


import com.lv.domain.auth.entity.AuthUserDo;

/**
* @author lvxueqiang
* @description 针对表【lv_auth_user(用户信息表)】的数据库操作Service
* @createDate 2023-07-14 00:56:19
*/
public interface AuthUserService {

    /**
     * 根据用户名获取用户信息
     * @param userName 用户名
     * @return 用户整体信息
     */
    AuthUserDo getUserByUsername(String userName);
}
