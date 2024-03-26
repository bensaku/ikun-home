package com.example.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.DTO.RegisterDTO;
import com.example.demo.DTO.RetrieveDTO;
import com.example.demo.entity.User;
import com.example.demo.DTO.UserLoginDTO;
import com.example.demo.mapper.IdentityMapper;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService {


    @Override
    public String loginbool(UserLoginDTO userLoginDTO) {
        String userNo = userLoginDTO.getUserNo();
        String userPassword = userLoginDTO.getUserPassword();

        User user = getById(userNo);
        if(user == null){
            return "密码错误";
        }
        if( user.getId()==null && user.getUserPassword().equals(userPassword)){
            return "账号未实名";
        }
        else if (!user.getUserPassword().equals(userPassword)) {
            return "密码错误";
        }
        else{
            return user.getUserType();
        }
    }
    @Override
    public boolean registerbool(RegisterDTO registerDTO) {
        String userNo = registerDTO.getUserNo();
        String userPassword = registerDTO.getUserPassword();
        String userMail = registerDTO.getUserMail();

        User user = new User();
        user.setUserNo(userNo);
        user.setUserPassword(userPassword);
        user.setUserType("普通用户");
        user.setUserMail(userMail);
        if (getById(userNo)!=null){
            return false;
        }
        boolean saved = save(user); // 保存用户信息并检查保存是否成功
        if (saved) {
            User savedUser = getById(userNo);
            return savedUser != null && savedUser.getUserPassword().equals(userPassword);
        } else {
            return false;
        }
    }

    @Override
    public boolean identity(String userno,String id) {
        User user = getById(userno);
        System.out.println(user);
        if(user.getId()==null){
            user.setId(id);
            updateById(user);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean retrievebool(RetrieveDTO retrieveDTO) {
        String userNo = retrieveDTO.getUserNo();
        String userPassword = retrieveDTO.getUserPassword();
        String code = retrieveDTO.getCode();
        if (getById(userNo)==null){
            return false;
        }
        User user = getById(userNo);
        user.setUserPassword(userPassword);

        if (!Objects.equals(user.getCode(), code)){
            return false;
        }
        else {
            user.setCode(null);
            return updateById(user);
        }

    }
}
