package com.example.demo.entity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("myfamily")
public class MyFamily {
    @TableId(value = "fam_id", type = IdType.AUTO)
    private String famId;
    private String userNo;
    private String famName;
    private String famNo;
    private String userType;

}