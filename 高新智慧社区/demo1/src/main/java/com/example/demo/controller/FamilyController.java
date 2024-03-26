package com.example.demo.controller;

import com.example.demo.entity.Family;
import com.example.demo.entity.MyFamily;
import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.FamilyService;
import com.example.demo.service.MyFamilyService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/family")
public class FamilyController {
    @Autowired
    private FamilyService familyService;

    @Resource
    private UserService userService;

    @Resource
    private MyFamilyService myFamilyService;

    @PostMapping("/addFam")
    public ResponseEntity<String> addFam(@RequestBody Family family){
        try {
            familyService.addFam(family);

            MyFamily myFamily = myFamilyService.getById(family.getFamId());
            User user = userService.getById(myFamily.getFamNo());
            if (user!=null) {
                if (user.getUserType().equals("普通用户")) {
                    user.setUserType("家属");
                    userService.updateById(user);
                    return ResponseEntity.ok("添加家人成功,已将家人的用户类型设置为'家属'");
                }
            }
            return ResponseEntity.ok("添加家人成功");

        } catch (DuplicateKeyException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("添加家人失败,该家属已存在");
        }
    }

    @DeleteMapping("/deleteFam")
    public ResponseEntity<String> deleteFam(@RequestParam("famId") String famId){
        MyFamily myFamily = myFamilyService.getById(famId);
        User user = userService.getById(myFamily.getFamNo());
        if (user!=null) {
            if (user.getUserType().equals("家属")) {
                user.setUserType("普通用户");
                userService.updateById(user);
            }
        }

        familyService.deleteFam(famId);

        return ResponseEntity.ok("删除家人成功");
    }
}
