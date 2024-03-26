package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.DTO.HouseCarPortDTO;
import com.example.demo.entity.CarPort;
import com.example.demo.entity.House;
import com.example.demo.entity.HouseCarPort;

import java.util.List;

public interface HouseCarPortService extends IService<HouseCarPort> {

    List<String> getByHouse(List<House> house);

    List<HouseCarPortDTO> getByCarPort(List<CarPort> carPort);
}
