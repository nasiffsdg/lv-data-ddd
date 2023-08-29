package com.lv.application.auth.impl;


import cn.hutool.core.collection.CollectionUtil;
import com.lv.application.auth.AuthUserService;
import com.lv.common.constants.AuthCacheNames;
import com.lv.common.constants.CacheConstant;
import com.lv.common.exceptions.UserAuthException;
import com.lv.common.service.EmailService;
import com.lv.common.service.R2mService;
import com.lv.common.utils.CodeGenerateUtils;
import com.lv.common.utils.MatcherUtil;
import com.lv.common.utils.SnowflakeIdUtil;
import com.lv.domain.auth.dto.RegisterDto;
import com.lv.domain.auth.entity.AuthUserDo;
import com.lv.infrastructure.auth.dao.AuthUserMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lvxueqiang
 * @description 针对表【lv_auth_user(用户信息表)】的数据库操作Service实现
 * @createDate 2023-08-27 16:32:40
 */
@Service
public class AuthUserServiceImpl implements AuthUserService {

    @Resource
    private AuthUserMapper authUserMapper;
    @Resource
    private R2mService r2mService;
    @Resource
    private EmailService emailService;
    private static final Integer CODE_EXPIRE_TIME = 60;
    private static final String USER_NO = "userNo-";

    @Override
    public List<AuthUserDo> getUserByUsernameOrEmal(String username) {

        // 判断是否是邮箱
        if (MatcherUtil.isEmailStrValid(username)){
            // 如果是邮箱
            return authUserMapper.getUserByEmail(username);
        }
        return authUserMapper.getUserByUsername(username);
    }

    @Override
    public void sendCode(String email) {
        // 判断是邮箱是否合法
        if (!MatcherUtil.isEmailStrValid(email)){
            throw new UserAuthException(UserAuthException.EMAIL_FALSE, "邮箱格式不合法");
        }
        // 判断用户邮箱是否被注册
        if (!CollectionUtil.isEmpty(authUserMapper.getUserByEmail(email))){
            throw new UserAuthException(UserAuthException.EMAIL_FALSE, "用户邮箱已经被注册");
        }
        //判断该用户是否存在验证码
        String userCacheKey = AuthCacheNames.CODE_KEY + CacheConstant.UNION_KEY + email;
        if (r2mService.hasKey(userCacheKey)){
            // 用户验证码已经存在
            throw new UserAuthException(UserAuthException.EMAIL_SEND_FALSE, "用户验证码已经存在");
        }
        // 获取验证码
        Integer code = CodeGenerateUtils.generateValidateCode();
        emailService.sendCode(email, code);
        // 用户验证码缓存
        r2mService.set(userCacheKey, code, CODE_EXPIRE_TIME);
    }

    @Override
    public void register(RegisterDto registerDto) {

        // 缓存key
        String userCacheKey = AuthCacheNames.CODE_KEY + CacheConstant.UNION_KEY + registerDto.getMail();
        // 获取用户验证码
        Object userCacheCode = r2mService.get(userCacheKey);
        if (!registerDto.getCode().equals(String.valueOf(userCacheCode))){
            throw new UserAuthException(UserAuthException.REGISTER_CODE_FALSE, "验证码有误");
        }
        // 用户信息落库
        if (!CollectionUtil.isEmpty(authUserMapper.getUserByUsername(registerDto.getUserName()))){
            throw new UserAuthException(UserAuthException.REGISTER_MESSAGE_FALSE, "您的用户名已经被占用");
        }
        if (!CollectionUtil.isEmpty(authUserMapper.getUserByEmail(registerDto.getMail()))){
            throw new UserAuthException(UserAuthException.REGISTER_MESSAGE_FALSE, "用户邮箱已经被注册");
        }
        AuthUserDo authUserDo = new AuthUserDo();
        // 设置用户号
        authUserDo.setUserNo(USER_NO + SnowflakeIdUtil.nextIdStr());
        authUserDo.setUserName(registerDto.getUserName());
        authUserDo.setUserPwd(registerDto.getUserPwd());
        authUserDo.setEMail(registerDto.getMail());
        authUserMapper.insertUser(authUserDo);
    }
}




