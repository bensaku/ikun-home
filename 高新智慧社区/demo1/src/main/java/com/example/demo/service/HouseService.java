package com.example.demo.service;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.entity.House;

import java.util.List;

public interface HouseService extends IService<House>{
    List<House> getByUser(String userNo);

    List<House> getByUserState(String userNo);

    void addHouse(House house);
}
