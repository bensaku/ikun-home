package com.example.demo;

import com.baomidou.mybatisplus.core.assist.ISqlRunner;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.DTO.HouseCarPortDTO;
import com.example.demo.DTO.RegisterDTO;
import com.example.demo.entity.*;
import com.example.demo.mapper.IdentityMapper;
import com.example.demo.mapper.ProblemMapper;
import com.example.demo.mapper.UserMapper;
import com.example.demo.mapper.VoteMapper;
import com.example.demo.service.*;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SpringBootTest
class Demo1ApplicationTests {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserService userService;
    @Autowired
    private RegisterDTO registerDTO;

    @Autowired
    private HouseService houseService;

    @Autowired
    private CarPortService carPortService;

    @Autowired
    private HouseCarPortService houseCarPortService;
@Autowired
private  ProblemService problemService;
    @Test
    void getbycarport(){
//        List<House> houses = houseService.getByUserState("bensaku");
//        if (houses == null || houses.isEmpty()) {
//
//        }
//        List<String> carstr = houseCarPortService.getByHouse(houses);
//        if (carstr == null || carstr.isEmpty()) {
//            System.out.println(Collections.emptyList());
//        }else {
//            List<CarPort> carPorts = carPortService.getCarPortsByList(carstr);
//            List<HouseCarPortDTO> dto = houseCarPortService.getByCarPort(carPorts);
//            System.out.println(dto);
//        }
        System.out.println(carPortService.getUsableCarPort());

    }

    @Test
    void asdui(){
        System.out.println(problemService.gerURLbyID("P000037"));
    }
}

//
//    @Test
//    void insertHouse(){
//        House house= new House();
//        house.setHousePlot("佩罗高地");
//        house.setHouseBuilding("一号楼");
//        house.setHouseUnit("一单元");
//        house.setHouseNumber("101");
//        house.setHouseProperty("rock物业");
//        house.setUserNo("bensaku");
//
//        houseService.addHouse(house);
//    }
//
//}