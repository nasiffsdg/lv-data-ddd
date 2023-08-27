package com.lv.infrastructure.auth.dao;


import com.lv.domain.auth.entity.AuthUserDo;

/**
* @author lvxueqiang
* @description 针对表【lv_auth_user(用户信息表)】的数据库操作Mapper
* @createDate 2023-07-14 00:56:19
* @Entity com.lv.domain.entity.AuthUserDo
*/
public interface AuthUserMapper {
    AuthUserDo selectByUsername(String userName);
    AuthUserDo selectUserById(Long id);
}




