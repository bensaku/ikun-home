package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("identity_data")
public class Identity implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private String Id;

    private String Name;
    private String Sex;
    private String Nation;
    private String bornDate;
    private String Address;
    private String Duetime;
}
