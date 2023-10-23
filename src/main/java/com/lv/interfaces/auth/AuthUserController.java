package com.lv.interfaces.auth;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.lv.application.auth.AuthUserService;
import com.lv.common.annotation.IgnoreToken;
import com.lv.common.constants.AuthCacheNames;
import com.lv.common.constants.CacheConstant;
import com.lv.common.domain.entity.ResponseInfo;
import com.lv.common.exceptions.UserAuthException;
import com.lv.common.service.R2mService;
import com.lv.common.utils.JWTUtil;
import com.lv.common.utils.MatcherUtil;
import com.lv.domain.auth.dto.AuthUserDto;
import com.lv.domain.auth.dto.RegisterDto;
import com.lv.domain.auth.entity.AuthUserDo;
import jakarta.annotation.Resource;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

/**
 * @author QiangZai
 * @version 1.0
 * @date 2023/7/14 1:02 AM
 */
@Log4j2
@IgnoreToken
@RestController
@RequestMapping("/auth/user")
public class AuthUserController {

    @Resource
    AuthUserService authUserService;
    @Resource
    R2mService r2mService;

    /**
     * 用户登陆
     *
     * @param username 用户名
     * @param password 密码
     * @return 用户信息
     */
    @IgnoreToken
    @GetMapping("/jwtLogin")
    public ResponseInfo login(String username, String password) {

        //1. 判断用户名密码是否为空
        if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
            log.error("用户名或者密码为空");
            throw new UserAuthException(UserAuthException.LOGIN_FALSE, "用户名或者密码为空");
        }
        //2. 验证用户
        AuthUserDo authUser = CollectionUtil.getFirst(authUserService.getUserByUsernameOrEmail(username));
        if (Objects.isNull(authUser)) {
            log.error("用户名或者密码错误");
            throw new UserAuthException(UserAuthException.LOGIN_FALSE, "用户名或者密码错误");
        }
        if (!password.equalsIgnoreCase(authUser.getUserPwd())) {
            log.error("用户名或者密码错误");
            throw new UserAuthException(UserAuthException.LOGIN_FALSE, "用户名或者密码错误");
        }
        //3. token授权
        String token = JWTUtil.createToken(authUser);
        AuthUserDto authUserDto = new AuthUserDto();
        authUserDto.setAuthUser(authUser);
        authUserDto.setAuthToken(token);
        //4. 缓存用户信息
        String userCacheKey = AuthCacheNames.USER_KEY + CacheConstant.UNION_KEY + token;
        r2mService.set(userCacheKey, authUserDto, JWTUtil.EXPIRE_DATE / 1000);
        return ResponseInfo.success(authUserDto);
    }

    /**
     * 用户信息注册
     * @param registerDto 用户信息
     */
    @IgnoreToken
    @PostMapping("/register")
    public ResponseInfo register(@RequestBody RegisterDto registerDto) {

        // 参数校验
        if (StrUtil.isBlank(registerDto.getUserName()) ||
                StrUtil.isBlank(registerDto.getMail()) ||
                StrUtil.isBlank(registerDto.getUserPwd()) ||
                StrUtil.isBlank(registerDto.getCode())||
                !MatcherUtil.isEmailStrValid(registerDto.getMail())) {
            throw new UserAuthException(UserAuthException.REGISTER_MESSAGE_FALSE, "注册参数有误");
        }
        // 信息注册
        authUserService.register(registerDto);
        return ResponseInfo.success();
    }

    /**
     * 更新用户密码
     * @param registerDto 用户信息
     */
    @IgnoreToken
    @PostMapping("/updatePassword")
    public ResponseInfo updatePassword(@RequestBody RegisterDto registerDto) {

        // 参数校验
        if (StrUtil.isBlank(registerDto.getMail()) ||
                StrUtil.isBlank(registerDto.getUserPwd()) ||
                StrUtil.isBlank(registerDto.getCode())||
                !MatcherUtil.isEmailStrValid(registerDto.getMail())) {
            throw new UserAuthException(UserAuthException.REGISTER_MESSAGE_FALSE, "邮箱信息有误");
        }
        // 信息注册
        authUserService.updatePassword(registerDto);
        return ResponseInfo.success();
    }

    /**
     * 获取登陆验证码
     * @param email 邮箱
     * @return 验证码
     */
    @IgnoreToken
    @GetMapping("/getCode")
    public ResponseInfo getCode(String email) {
        authUserService.sendCode(email);
        return ResponseInfo.success();
    }
}
