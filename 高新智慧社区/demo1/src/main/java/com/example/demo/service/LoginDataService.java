package com.example.demo.service;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.DTO.UserLoginDTO;
import com.example.demo.entity.LoginData;

public interface LoginDataService extends IService<LoginData>{
    LoginData getByUserId(UserLoginDTO userLoginDTO);
}
