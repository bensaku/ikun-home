package com.example.demo.controller;

import com.example.demo.DTO.IdentityDTO;

import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.FamilyService;
import com.example.demo.service.RealNameService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/identity")
public class RealNameController {
    @Resource
    private RealNameService realNameService;
    @Resource
    private UserService userService;
    @Resource
    private FamilyService familyService;
    @Resource
    private UserMapper userMapper;
    @PostMapping("/manual")
    public String RealName(@RequestBody IdentityDTO identityDTO) {
        String userno = identityDTO.getUserNo();
        String id = identityDTO.getId();
        System.out.println(identityDTO);
        if(realNameService.realname(identityDTO)){
            if(userService.identity(userno,id)){
                if(familyService.realname(id)){
                    new User();
                    User user = userMapper.selectById(userno);
                    user.setUserType("家属");
                    userMapper.updateById(user);
                    return "成为家属";
                }
                else {return "实名成功";}
            }
            else
            {
                return "该用户已实名";
            }
        }
        else {
            return "该身份数据已注册";
        }
    }
}
