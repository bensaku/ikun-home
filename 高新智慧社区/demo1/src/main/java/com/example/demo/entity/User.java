package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("user")
public class User implements Serializable {
    @TableId(value = "user_no", type = IdType.AUTO)
    private String userNo;

    private String userPassword;
    private String userType;
    private String userMail;
    private String id;
    private String code;

}
