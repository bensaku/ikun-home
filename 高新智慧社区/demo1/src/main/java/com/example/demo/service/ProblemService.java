package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.entity.Problem;

import java.util.List;

public interface ProblemService extends IService<Problem> {
    List<Problem> getAllProblem();
    void addPro(Problem problem);
    boolean deleteFile(String filePath);
    List<Problem> selectByDateRange(String problemReporttime, String problemFinishtime);
    List<Problem> getByUserNo(String userNo);

    String gerURLbyID(String id);
}
