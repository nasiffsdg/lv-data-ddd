package com.lv.application.auth.impl;

import com.lv.application.auth.AuthUserService;
import com.lv.domain.auth.entity.AuthUserDo;
import com.lv.infrastructure.auth.dao.AuthUserMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
* @author lvxueqiang
* @description 针对表【lv_auth_user(用户信息表)】的数据库操作Service实现
* @createDate 2023-07-14 00:56:19
*/
@Service
public class AuthUserServiceImpl implements AuthUserService {

    @Resource
    AuthUserMapper authUserMapper;
    @Override
    public AuthUserDo getUserByUsername(String userName) {
        return authUserMapper.selectByUsername(userName);
    }
}




