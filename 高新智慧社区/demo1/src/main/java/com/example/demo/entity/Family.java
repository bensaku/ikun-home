package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("family")
public class Family {
    @TableId(value = "fam_id", type = IdType.AUTO)
    private String famId;

    private String userNo;
    private String famName;

}
