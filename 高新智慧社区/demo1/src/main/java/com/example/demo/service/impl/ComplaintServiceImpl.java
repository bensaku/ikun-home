package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.entity.Complaint;
import com.example.demo.entity.Vote;
import com.example.demo.mapper.ComplaintMapper;
import com.example.demo.mapper.VoteMapper;
import com.example.demo.service.ComplaintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class ComplaintServiceImpl extends ServiceImpl<ComplaintMapper, Complaint> implements ComplaintService {
    private final ComplaintMapper complaintMapper;
    @Autowired
    public ComplaintServiceImpl(ComplaintMapper complaintMapper) {
        this.complaintMapper=complaintMapper;
    }
    @Override
    public List<Complaint> getAllComplaint() {
        return list();
    }
    @Override
    public List<Complaint> getByUserNo(String userNo) {
        QueryWrapper<Complaint> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_no", userNo);
        return baseMapper.selectList(queryWrapper);
    }

    @Override
    public void addCom(Complaint complaint) {
        complaint.setComplaintState("未处理");
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String dateTimeStr = localDateTime.format(formatter);
        complaint.setComplaintReportTime(dateTimeStr);
        baseMapper.insert(complaint);
    }

    @Override
    public List<Complaint> selectByDateRange(String complaintReporttime, String complaintFinishtime) {
        List<Complaint> complaints = complaintMapper.selectByDateRange(complaintReporttime,complaintFinishtime);
        System.out.println(complaints);
        return complaints;
    }
}
