package com.example.demo.controller;

import com.example.demo.entity.Family;
import com.example.demo.entity.House;
import com.example.demo.entity.MyFamily;
import com.example.demo.entity.User;
import com.example.demo.mapper.HouseMapper;
import com.example.demo.service.HouseService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/house")
public class HouseController {

    @Resource
    private HouseService houseService;

    @Resource
    private UserService userService;

    @GetMapping("/getByUser")
    public List<House> getByUser(@RequestParam("userNo") String userNo){
        return houseService.getByUser(userNo);
    }

    @GetMapping("/getByUserState")
    public List<House> getByUserState(@RequestParam("userNo") String userNo){
        return houseService.getByUserState(userNo);
    }


    // 定义常量
public static final String NORMAL_USER = "普通用户";
public static final String FAMILY_USER = "家属";
public static final String OWNER_USER = "业主";
public static final String ADD_HOUSE_SUCCESS = "添加房屋成功";
public static final String ADD_HOUSE_FAIL = "添加房屋失败,该房屋信息已存在";
public static final String CHANGE_USER_TYPE = "已将您的用户类型设置为'";

@PostMapping("/addHouse")
public ResponseEntity<String> addHouse(@RequestBody House house){
    try {
        // 使用 try-with-resources 语句
        try (Connection conn = dataSource.getConnection()) {
            houseService.addHouse(house);
            User user = userService.getById(house.getUserNo());
            // 判断用户类型
            if (user.getUserType().equals(NORMAL_USER) || user.getUserType().equals(FAMILY_USER)) {
                user.setUserType(OWNER_USER);
                // 抽取重复代码
                updateUserAndReturn(user, ADD_HOUSE_SUCCESS + CHANGE_USER_TYPE + OWNER_USER + "'");
            }
            // 抽取重复代码
            return okResponse(ADD_HOUSE_SUCCESS);
        }
    } catch (DuplicateKeyException e) {
        // 抽取重复代码
        return errorResponse(ADD_HOUSE_FAIL);
    }
}

// 抽取重复代码
private void updateUserAndReturn(User user, String message) {
    userService.updateById(user);
    return okResponse(message);
}

// 抽取重复代码
private ResponseEntity<String> okResponse(String message) {
    return ResponseEntity.ok(message);
}

// 抽取重复代码
private ResponseEntity<String> errorResponse(String message) {
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
}


}
