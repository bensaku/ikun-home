package com.example.demo.controller;

import com.example.demo.DTO.RegisterDTO;
import com.example.demo.DTO.UserLoginDTO;
import com.example.demo.entity.Family;
import com.example.demo.entity.LoginData;
import com.example.demo.service.LoginDataService;
import com.example.demo.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Resource
    private UserService userService;

    @Resource
    private LoginDataService loginDataService;


    @PostMapping("/login")
    public Object login(@RequestBody UserLoginDTO userLoginDTO) {

        if(userService.loginbool(userLoginDTO)=="账号未实名"){
            return "账号未实名";
        }
        else if (userService.loginbool(userLoginDTO)=="密码错误") {
            return "密码错误";
        }
        else {
            return loginDataService.getByUserId(userLoginDTO);
        }
    }
    @PostMapping("/register")
    public String register(@RequestBody RegisterDTO registerDTO) {

        if (userService.registerbool(registerDTO)) {
            return "注册成功";
        } else {
            return "注册失败";
        }
    }

}
