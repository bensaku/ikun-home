package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("house_carport")
public class HouseCarPort {

    private String housePlot;
    private String houseBuilding;
    private String houseUnit;
    private String houseNumber;
    private String carPort;
    private Integer houseNo;

}
