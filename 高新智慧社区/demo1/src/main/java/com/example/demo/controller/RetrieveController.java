package com.example.demo.controller;

import com.example.demo.DTO.RegisterDTO;
import com.example.demo.DTO.RetrieveDTO;
import com.example.demo.DTO.UserLoginDTO;
import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.UserService;
import com.example.demo.service.impl.EmailServiceImpl;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Random;

@RestController
@RequestMapping("/api")
public class RetrieveController {
    @Resource
    private EmailServiceImpl emailService;
    @Resource
    private UserMapper userMapper;
    @Resource
    private UserService userService;
    @PostMapping("/sendcode")
    public String sendVerificationCode(@RequestBody UserLoginDTO userLoginDTO) {
        // 生成验证码逻辑...
        String mail = "k20140310@qq.com";
        Random random = new Random();
        // 生成一个 0 到 999999（不包含）之间的随机整数
        int randomNumber = random.nextInt(999999);
        // 如果随机数不足六位，则在前面补0
        String code = String.format("%06d", randomNumber);

        //存入数据库
        String userno = userLoginDTO.getUserNo();
        User user = userMapper.selectById(userno);
        user.setCode(code);
        userMapper.updateById(user);

        // 发送验证码邮件
        if(emailService.sendVerificationCode(mail, code)){
            return "成功";
        }else {
            return "失败";
        }
    }
    @PostMapping("/retrieve")
    public String register(@RequestBody RetrieveDTO retrieveDTO) {

        if (userService.retrievebool(retrieveDTO)) {

            return "找回成功";
        } else {
            return "找回失败";
        }
    }
}
