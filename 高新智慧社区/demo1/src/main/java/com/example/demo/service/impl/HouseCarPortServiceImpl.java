package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.DTO.HouseCarPortDTO;
import com.example.demo.entity.CarPort;
import com.example.demo.entity.House;
import com.example.demo.entity.HouseCarPort;
import com.example.demo.mapper.HouseCarPortMapper;
import com.example.demo.service.HouseCarPortService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class HouseCarPortServiceImpl extends ServiceImpl<HouseCarPortMapper, HouseCarPort> implements HouseCarPortService {

    @Resource
    private HouseCarPortMapper houseCarPortMapper;
    @Override
    public List<String> getByHouse(List<House> house){
        List<String> carPortList = new ArrayList<>();

        for (House hs : house) {
            QueryWrapper<HouseCarPort> queryWrapper = new QueryWrapper<>();
            queryWrapper.select("car_port")
                    .eq("house_plot", hs.getHousePlot())
                    .eq("house_building", hs.getHouseBuilding())
                    .eq("house_unit", hs.getHouseUnit())
                    .eq("house_number", hs.getHouseNumber());

            List<HouseCarPort> result = houseCarPortMapper.selectList(queryWrapper);

            for (HouseCarPort houseResult : result) {
                carPortList.add(houseResult.getCarPort());
            }
        }
        return carPortList;
    }

    @Override
    public List<HouseCarPortDTO> getByCarPort(List<CarPort> carPort){
        List<HouseCarPortDTO> dto = new ArrayList<>();

        for (CarPort cp: carPort) {
            QueryWrapper<HouseCarPort> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("car_port", cp.getCarPort());
            List<HouseCarPort> result = houseCarPortMapper.selectList(queryWrapper);

            HouseCarPortDTO houseCarPortDTO = new HouseCarPortDTO();
            houseCarPortDTO.setCarPort(cp.getCarPort());
            houseCarPortDTO.setCarNo(cp.getCarNo());
            houseCarPortDTO.setCarDetail(cp.getCarDetail());
            houseCarPortDTO.setCarCharge(cp.getCarCharge());
            houseCarPortDTO.setCarTime(cp.getCarTime());
            houseCarPortDTO.setCarPosition(cp.getCarPosition());

            List<Integer> houseNo = new ArrayList<>();
            List<String> house = new ArrayList<>();
            for (HouseCarPort hR : result) {
                houseNo.add(hR.getHouseNo());
                String str = hR.getHousePlot()+hR.getHouseBuilding()+hR.getHouseUnit()+hR.getHouseNumber();
                house.add(str);
            }
            houseCarPortDTO.setHouseNo(houseNo);
            houseCarPortDTO.setHouse(house);
            dto.add(houseCarPortDTO);
        }
        return dto;

    }
}
