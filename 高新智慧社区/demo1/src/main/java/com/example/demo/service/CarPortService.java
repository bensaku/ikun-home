package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.entity.CarPort;
import com.example.demo.entity.House;

import java.util.List;

public interface CarPortService extends IService<CarPort> {

    List<CarPort> getCarPortsByList(List<String> carPortList);

    List<CarPort> getUsableCarPort();
}
