package com.lv.interfaces.auth;

import com.lv.application.auth.AuthUserService;
import com.lv.common.constants.AuthCacheNames;
import com.lv.common.constants.CacheConstant;
import com.lv.common.constants.ResponseCode;
import com.lv.common.domain.entity.ResponseInfo;
import com.lv.common.service.R2mService;
import com.lv.common.utils.JWTUtil;
import com.lv.domain.auth.dto.AuthUserDto;
import com.lv.domain.auth.entity.AuthUserDo;
import jakarta.annotation.Resource;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

/**
 * @author QiangZai
 * @version 1.0
 * @date 2023/7/14 1:02 AM
 */
@Log4j2
@RestController
@RequestMapping("/auth/user")
public class AuthUserController {

    @Resource
    AuthUserService authUserService;
    @Resource
    R2mService r2mService;
    @GetMapping("/jwtLogin")
    public ResponseInfo login(String username, String password){

        //1. 判断用户名密码是否为空
        if(StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
            return ResponseInfo.fail(ResponseCode.UNAUTHORIZED,"用户名或者密码为空");
        }
        //2. 验证用户
        AuthUserDo authUser = authUserService.getUserByUsername(username);
        if(Objects.isNull(authUser)) {
            log.warn("用户名或者密码错误");
            return ResponseInfo.fail(ResponseCode.UNAUTHORIZED, "用户名或者密码错误");
        }
        if (!password.equalsIgnoreCase(authUser.getUserPwd())){
            log.warn("用户名或者密码错误");
            return ResponseInfo.fail(ResponseCode.UNAUTHORIZED,"用户名或者密码错误");
        }
        //3. token授权
        String token = JWTUtil.createToken(authUser);
        AuthUserDto authUserDto = new AuthUserDto();
        authUserDto.setAuthUser(authUser);
        authUserDto.setAuthToken(token);
        //4. 缓存用户信息
        String userCacheKey = AuthCacheNames.USER_KEY + CacheConstant.UNION_KEY + token;
        r2mService.set(userCacheKey, authUserDto, JWTUtil.EXPIRE_DATE/1000);
        return ResponseInfo.success(authUserDto);
    }
}
