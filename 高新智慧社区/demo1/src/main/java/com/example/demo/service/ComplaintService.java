package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.entity.Complaint;
import com.example.demo.entity.Problem;
import com.example.demo.entity.Vote;

import java.util.List;

public interface ComplaintService extends IService<Complaint> {
    List<Complaint> getAllComplaint();
    List<Complaint> getByUserNo(String userNo);
    void addCom(Complaint complaint);

    List<Complaint> selectByDateRange(String complaintReporttime, String complaintFinishtime);
}
