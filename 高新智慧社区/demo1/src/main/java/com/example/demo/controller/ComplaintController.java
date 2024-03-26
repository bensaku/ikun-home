package com.example.demo.controller;

import com.example.demo.entity.Complaint;
import com.example.demo.entity.Problem;
import com.example.demo.service.ComplaintService;
import com.example.demo.service.ProblemService;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/complaint")
public class ComplaintController {

    @Resource
    private ComplaintService complaintService ;
    @GetMapping("/getAll")
    public List<Complaint> getComplaints() {
        // 调用MyBatis Plus的查询方法来获取问题列表
        return complaintService.getAllComplaint();
    }

    @GetMapping("/getByUserNo")
    public List<Complaint> getByUserNo(@RequestParam("userNo") String userNo) {
        return complaintService.getByUserNo(userNo);
    }

    @PostMapping("/addCom")
    public ResponseEntity<String> addCom(@RequestBody Complaint complaint){
        try {
            complaintService.addCom(complaint);
            return ResponseEntity.ok("投诉成功");
        } catch (DuplicateKeyException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("投诉失败");
        }
    }

    @GetMapping("/getComplaintsByDateRange")
    public List<Complaint> selectByDateRange(@RequestParam String complaintReporttime,@RequestParam String complaintFinishtime)
    {
        return  complaintService.selectByDateRange(complaintReporttime,complaintFinishtime);
    }
}
