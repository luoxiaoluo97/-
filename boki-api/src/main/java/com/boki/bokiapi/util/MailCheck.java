package com.boki.bokiapi.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * @time: 2020/2/15
 * @author: LJF
 * @description:邮箱注册验证码发送
 */

//@PropertySource(value={"classpath:application.properties"})
//@PropertySource(value={"classpath:application.yml"})
@Component
public class MailCheck {


    @Value("${spring.mail.username}")
    private String username;
//
//    @Value("#{1123}")
//    private int a;
//    @Autowired
//    private Mail mail;


    public String mailSend(String targetMail){
        String code = randomCode();
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject("这是一封来自BOKI论坛的邮件");
        message.setText("本次验证码为"+code+",如非本人操作，请忽略。");
        message.setFrom("2500259143@qq.com");
        message.setTo("1270678164@qq.com");

        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.qq.com");
        mailSender.setUsername("2500259143@qq.com");
        mailSender.setPassword("kkmcfiljavcpdjgg");
        mailSender.setDefaultEncoding("utf-8");
        try{
            //mailSender.send(message);
        }catch (Exception e){
            System.out.println("发送失败");
        }
        return code;
    }

    /**
     * 四位数验证码
     * @return
     */
    public String randomCode(){
        Random random = new Random();
        String code = random.nextDouble() + "";
        code = code.substring(code.length()-4);
        return code;
    }


}
