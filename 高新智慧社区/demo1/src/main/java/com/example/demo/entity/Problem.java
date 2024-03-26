package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("problem")
public class Problem {
    @TableId(value = "problem_no", type = IdType.AUTO)
    public  String  problemNo;

    @TableField
    public String problemType;
    public String problemState;
    public String problemReporttime;
    public String problemFinishtime;
    public String problemDetail;
    public String userNo;
    public String problemAnnex;

    public void setProblemReportTime(String problemReportTime) {
        this.problemReporttime = problemReportTime;
    }
}
