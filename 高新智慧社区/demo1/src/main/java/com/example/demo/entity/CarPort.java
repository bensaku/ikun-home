package com.example.demo.entity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("carport")
public class CarPort {
    @TableId(value = "car_port")
    private String carPort;
    private String carNo;
    private String carDetail;
    private String carCharge;
    private String carTime;
    private String carPosition;
}
