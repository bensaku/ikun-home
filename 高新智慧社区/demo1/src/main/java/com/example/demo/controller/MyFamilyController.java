package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.entity.Identity;
import com.example.demo.entity.MyFamily;
import com.example.demo.mapper.IdentityMapper;
import com.example.demo.service.FamilyService;
import com.example.demo.service.MyFamilyService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/myfamily")
public class MyFamilyController {
    @Autowired
    private MyFamilyService myFamilyService;

    @Resource
    private UserService userService;

    @Resource
    private FamilyService familyService;

    @Resource
    private IdentityMapper identityMapper;

    @GetMapping("/getByUserNo")
    public List<MyFamily> getByUserNo(@RequestParam("userNo") String userNo) {
        return myFamilyService.getByUserNo(userNo);
    }

    @GetMapping("/getByFamNo")
    public List<MyFamily> getByFamNo(@RequestParam("userNo") String famNo) {
        Optional<String> userNoOptional = myFamilyService.getUserNo(famNo);
        List<MyFamily> myFamilies = myFamilyService.getByFamNo(famNo);
        if (userNoOptional.isPresent()) {
            String userNo = userNoOptional.get();
            // 执行需要返回值的操作
             String id = userService.getById(userNo).getId();
             String userType = userService.getById(userNo).getUserType();
             String name = identityMapper.selectById(id).getName();
             MyFamily myFamily = new MyFamily();
             myFamily.setFamId(id);
             myFamily.setFamNo(userNo);
             myFamily.setUserType(userType);
             myFamily.setFamName(name);
             myFamilies.add(myFamily);
        }
        return  myFamilies;
    }
}
