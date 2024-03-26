package com.example.demo.controller;


import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.demo.DTO.HouseBindDTO;
import com.example.demo.DTO.HouseCarPortDTO;
import com.example.demo.entity.CarPort;
import com.example.demo.entity.House;
import com.example.demo.entity.HouseCarPort;
import com.example.demo.entity.User;
import com.example.demo.service.CarPortService;
import com.example.demo.service.HouseCarPortService;
import com.example.demo.service.HouseService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/carport")
public class CarPortController {

    @Resource
    private CarPortService carPortService;

    @Resource
    private HouseService houseService;

    @Resource
    private HouseCarPortService houseCarPortService;

    @GetMapping("/getCar")
    List<HouseCarPortDTO> getCar(@RequestParam("userNo") String userNo){
        List<House> houses = houseService.getByUserState(userNo);
        List<String> carStringList = houseCarPortService.getByHouse(houses);
        if (carstr == null || carstr.isEmpty()) {
            return(Collections.emptyList());
        }else {
            List<CarPort> carPorts = carPortService.getCarPortsByList(carstr);
            return houseCarPortService.getByCarPort(carPorts);
        }
    }

    @GetMapping("/getUsableCarPort")
    List<CarPort> getUsableCarPort(){
        return carPortService.getUsableCarPort();
    }

    @PostMapping("/addCar")
    public ResponseEntity<String> addCar(@RequestBody HouseCarPortDTO houseCarPortDTO){
        try {
            // 创建 UpdateWrapper 对象
            UpdateWrapper<CarPort> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("car_port", houseCarPortDTO.getCarPort());

            // 设置要更新的字段和新的值
            CarPort newCarPort = new CarPort();
            newCarPort.setCarNo(houseCarPortDTO.getCarNo());
            newCarPort.setCarDetail(houseCarPortDTO.getCarDetail());
            newCarPort.setCarTime(houseCarPortDTO.getCarTime());

            // 调用 Service 方法执行更新操作
            carPortService.update(newCarPort, updateWrapper);

            HouseCarPort houseCarPort = new HouseCarPort();
            houseCarPort.setCarPort(houseCarPortDTO.getCarPort());
            houseCarPort.setHouseNo(houseCarPortDTO.getHouseNo().get(0));
            House house = houseService.getById(houseCarPortDTO.getHouseNo().get(0));
            houseCarPort.setHousePlot(house.getHousePlot());
            houseCarPort.setHouseBuilding(house.getHouseBuilding());
            houseCarPort.setHouseUnit(house.getHouseUnit());
            houseCarPort.setHouseNumber(house.getHouseNumber());

            houseCarPortService.save(houseCarPort);


            return ResponseEntity.ok("添加车辆成功");

        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("添加失败,该车辆信息已存在");
        }
    }

    @PostMapping("/addTime")
    public ResponseEntity<String> addTime(@RequestBody CarPort carPort){
        // 创建 UpdateWrapper 对象
        UpdateWrapper<CarPort> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("car_port", carPort.getCarPort());

        // 设置要更新的字段和新的值
        CarPort newCarPort = new CarPort();
        newCarPort.setCarTime(carPort.getCarTime());
        // 调用 Service 方法执行更新操作
        carPortService.update(newCarPort, updateWrapper);
        return ResponseEntity.ok("续租成功");

    }

    @PostMapping("/bindHouse")
    public ResponseEntity<String> bindHouse(@RequestBody HouseBindDTO houseBindDTO){
        String carport = houseBindDTO.getCarPort();
        List<House> houses = houseBindDTO.getHouses();
        HouseCarPort houseCarPort = new HouseCarPort();
        for(House house:houses){
            houseCarPort.setCarPort(carport);
            houseCarPort.setHouseNo(house.getHouseNo());
            houseCarPort.setHousePlot(house.getHousePlot());
            houseCarPort.setHouseBuilding(house.getHouseBuilding());
            houseCarPort.setHouseUnit(house.getHouseUnit());
            houseCarPort.setHouseNumber(house.getHouseNumber());
            houseCarPortService.save(houseCarPort);
        }
        return ResponseEntity.ok("绑定房屋成功");
    }

}
