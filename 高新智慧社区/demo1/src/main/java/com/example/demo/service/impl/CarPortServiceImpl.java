package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.entity.CarPort;
import com.example.demo.entity.House;
import com.example.demo.mapper.CarPortMapper;
import com.example.demo.service.CarPortService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CarPortServiceImpl extends ServiceImpl<CarPortMapper, CarPort> implements CarPortService {

    @Resource
    private CarPortMapper carPortMapper;
    @Override
    public List<CarPort> getCarPortsByList(List<String> carPortList) {
        QueryWrapper<CarPort> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("car_port", carPortList);
        queryWrapper.groupBy("car_port");
        return carPortMapper.selectList(queryWrapper);
    }

    @Override
    public List<CarPort> getUsableCarPort(){
        QueryWrapper<CarPort> queryWrapper = new QueryWrapper<>();
        queryWrapper.isNull("car_no");
        return carPortMapper.selectList(queryWrapper);
    }

}
