package com.example.demo.service.impl;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.DTO.UserLoginDTO;
import com.example.demo.entity.LoginData;
import com.example.demo.mapper.LoginDataMapper;
import com.example.demo.service.LoginDataService;
import org.springframework.stereotype.Service;
@Service
public class LoginDataServiceImpl extends ServiceImpl<LoginDataMapper, LoginData> implements LoginDataService {
    @Override
    public LoginData getByUserId(UserLoginDTO userLoginDTO){
        String userNo = userLoginDTO.getUserNo();
        return getById(userNo);
    }
}
