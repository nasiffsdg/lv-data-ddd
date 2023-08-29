package com.lv.common.service;

import com.lv.common.exceptions.UserAuthException;
import jakarta.annotation.Resource;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeUtility;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * 邮件服务
 * @author QiangZai
 * @version 1.0
 * @date 2023/8/27 9:31 PM
 */
@Log4j2
@Service
public class EmailService {
    @Resource
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String username;

    /**
     * 发送邮件
     * @param email 邮箱
     */
    public void sendCode(String email, Integer code){
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setSubject("LC代码解析平台");
            message.setText("尊敬的用户您好!\n\n感谢您的使用。\n\n尊敬的: "+email+"您的校验验证码为: "+code+",有效期1分钟，请不要把验证码信息泄露给其他人,如非本人请勿操作");
            message.setTo(email);
            message.setFrom(new InternetAddress(MimeUtility.encodeText("LC")+"<"+username+">").toString());
            javaMailSender.send(message);
        }catch (Exception messagingException){
            log.error("邮件发送异常:{}", messagingException.getMessage());
            throw new UserAuthException(messagingException, UserAuthException.EMAIL_SEND_FALSE, "邮件发送异常");
        }
    }
}
