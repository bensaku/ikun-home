package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.DTO.RegisterDTO;
import com.example.demo.DTO.RetrieveDTO;
import com.example.demo.entity.User;
import com.example.demo.DTO.UserLoginDTO;

public interface UserService extends IService<User>{
    String loginbool(UserLoginDTO userLoginDTO);
    boolean registerbool(RegisterDTO registerDTO);

    boolean identity(String userno,String id);

    boolean retrievebool(RetrieveDTO retrieveDTO);
}
