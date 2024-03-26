package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("complaint")
public class Complaint {
    @TableId(value = "complaint_no", type = IdType.AUTO)
    public  String  complaintNo;
    public  String  complaintState;
    public  String  complaintReporttime;
    public  String  complaintFinishtime;
    public  String  complaintDetail;
    public  String  userNo;
    public  String  complaintFeedback;

    public void setComplaintReportTime(String complaintReportTime) {
        this.complaintReporttime = complaintReportTime;
    }
}
