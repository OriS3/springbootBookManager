package com.example.service.impl;

import com.example.service.VerifyService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
@Log
public class VerifyServiceImpl implements VerifyService {
    @Resource
    JavaMailSender sender;
    @Resource
    StringRedisTemplate template;
    @Value("${spring.mail.username}")
    String from;

    @Override
    public void sendVerifyCode(String mail) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject("【XX网站】 您的注册验证码");

        Random random = new Random();
        int code = random.nextInt(899999) + 10000;
        template.opsForValue().set("verifycode:" + mail, code+"", 1, TimeUnit.MINUTES);

        message.setText("您的验证码为 " + code + " 三分钟内有效");
        message.setFrom("NeoCheung023@126.com");
        message.setTo(mail);
        log.info("邮箱已发送到：" + mail);


        sender.send(message);
    }

    @Override
    public boolean checkCode(String email, String code) {
        return code.equals(template.opsForValue().get("verifycode:" + email));
    }
}
