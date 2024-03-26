package com.example.demo.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import javax.annotation.Resource;

@Service
public class EmailServiceImpl {
    @Resource
    private JavaMailSender javaMailSender;
    public boolean sendVerificationCode(String mail, String code) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(mail); // 设置收件人的电子邮件地址
        message.setSubject("验证码"); // 设置邮件主题
        message.setText("您的验证码是：" + code); // 设置邮件正文内容
        message.setFrom("2531768325@qq.com");
        javaMailSender.send(message); // 发送邮件
        return true;
    }

}
