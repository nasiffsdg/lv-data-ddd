package com.lv.common.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.InvalidClaimException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.lv.common.domain.entity.ResponseInfo;
import com.lv.domain.auth.entity.AuthUserDo;
import lombok.extern.log4j.Log4j2;

import java.time.Instant;
import java.util.Date;

/**
 * @author QiangZai
 * @version 1.0
 * @date 2023/7/14 1:30 AM
 */
@Log4j2
public class JWTUtil {

    // 密钥
    private static final String SECRET = "!Q@W#E$R^Y&U";
    //token签发者
    private static final String ISSUSRE = "HZSTYGZPT";
    //token过期时间 2 小时
    public static final Long EXPIRE_DATE = 1000 * 60L * 60 * 2;


    /**
     * 生成token
     */
    public static String createToken(AuthUserDo authUserDo) {

        //当前时间作为签发时间
        Date now = new Date();
        //加密算法
        Algorithm algorithm = Algorithm.HMAC256(SECRET);
        return JWT.create()
                .withIssuer(ISSUSRE)
                .withIssuedAt(now)
                .withExpiresAt(new Date(now.getTime() + EXPIRE_DATE))
                .withClaim("username", authUserDo.getUserName())
                .sign(algorithm);
    }

    /**
     * 验证token
     * 验证过程中如果有异常，则抛出；
     * 如果没有,则返回 DecodedJWT 对象来获取用户信息;
     */
    public static ResponseInfo verifyToken(String token, String username) {
        Algorithm algorithm = Algorithm.HMAC256(SECRET);
        try {
            JWTVerifier jwtVerifier = JWT.require(algorithm).withClaim("username", username).build();
            jwtVerifier.verify(token);
            return ResponseInfo.success();
        } catch (SignatureVerificationException e) {
            //验证的签名不一致
            throw new SignatureVerificationException(algorithm);
        } catch (TokenExpiredException e) {
            log.error("token 已经过期" + Instant.now());
        } catch (AlgorithmMismatchException e) {
            log.error("签名算法不匹配");
        } catch (InvalidClaimException e) {
            log.error("校验的claims内容不匹配");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseInfo.fail("用户和jwt-token校验失败");
    }
}

