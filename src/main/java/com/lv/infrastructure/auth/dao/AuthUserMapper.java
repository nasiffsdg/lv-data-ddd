package com.lv.infrastructure.auth.dao;


import com.lv.domain.auth.entity.AuthUserDo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @author lvxueqiang
* @description 针对表【lv_auth_user(用户信息表)】的数据库操作Mapper
* @createDate 2023-08-27 16:32:40
* @Entity com.lv.domain.auth.entity.AuthUser
*/
@Mapper
public interface AuthUserMapper{

    /**
     * 根据用户名获取用户信息
     * @param username 用户名
     * @return 信息
     */
    List<AuthUserDo> getUserByUsername(String username);

    /**
     * 根据邮箱获取用户信息
     * @param username 邮箱
     * @return 用户信息
     */
    List<AuthUserDo> getUserByEmail(String username);

    /**
     * 用户信息插入
     * @param authUserDo 用户信息
     */
    void insertUser(AuthUserDo authUserDo);
}




