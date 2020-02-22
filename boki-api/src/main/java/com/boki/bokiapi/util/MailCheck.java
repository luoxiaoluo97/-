package com.boki.bokiapi.util;

import com.boki.bokiapi.execption.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * @time: 2020/2/15
 * @author: LJF
 * @description:邮箱注册验证码发送
 */

@Slf4j
@Component
public class MailCheck {

    @Autowired
    private JavaMailSenderImpl mailSender;

    public String mailSend(final String targetMail){
        String code= randomCode();
        try{
            SimpleMailMessage message = new SimpleMailMessage();
            message.setSubject("这是一封来自BOKI论坛的邮件");
            message.setText("本次验证码为"+code+",该验证码5分钟内有效。如非本人操作，请忽略。");
            message.setFrom(mailSender.getUsername());
            message.setTo(targetMail);
            mailSender.send(message);
        }catch (MailException e){
            BusinessException exception = new BusinessException(targetMail+"验证码发送失败，原因为:\n"+e.getMessage());
            /*地址格式错误判断*/
            if(e.getMessage().indexOf("Invalid Addresses") > 0){
                exception.setType("ERROR_MAIL");
            }
            throw exception;
        }
        return code;
    }

    /**
     * 8位数验证码
     * @return
     */
    public String randomCode(){

        Random random = new Random();
        String code = random.nextDouble() + "";
        code = code.substring(code.length()-8);
        return code;
    }

    /**
     * 获取缓存中的邮箱验证码
     * @param mail
     * @return
     */
    public String getCheckCodeFromCache(String mail){
        return null;
    }
}
