package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.mapper.HouseMapper;
import com.example.demo.service.HouseService;
import com.example.demo.entity.House;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HouseServiceImpl extends ServiceImpl<HouseMapper,House> implements HouseService {

    public List<House> getByUser(String userNo){
        QueryWrapper<House> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_no",userNo);
        queryWrapper.orderByAsc("house_state");
        return list(queryWrapper);
    }

    public List<House> getByUserState(String userNo){
        QueryWrapper<House> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_no",userNo);
        queryWrapper.eq("house_state","已绑定");
        return list(queryWrapper);
    }
    public void addHouse(House house){
            save(house);
    }

}
