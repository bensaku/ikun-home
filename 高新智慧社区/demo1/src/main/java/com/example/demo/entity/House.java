package com.example.demo.entity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("house")
public class House {

    // 不需要声明 houser_no 主键，因为数据库中 house_no 是自增的
    @TableId(value = "house_no",type = IdType.AUTO)
    private Integer houseNo;
    private String housePlot;
    private String houseBuilding;
    private String houseUnit;
    private String houseNumber;
    private String userNo;
    private String houseProperty;
    private String houseState;
}
