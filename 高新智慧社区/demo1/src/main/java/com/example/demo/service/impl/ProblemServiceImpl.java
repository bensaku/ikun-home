package com.example.demo.service.impl;
import com.baidu.aip.nlp.AipNlp;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.entity.Problem;
import com.example.demo.mapper.ComplaintMapper;
import com.example.demo.mapper.ProblemMapper;
import com.example.demo.service.ProblemService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;

@Service
public  class ProblemServiceImpl  extends ServiceImpl<ProblemMapper,Problem> implements ProblemService {
    private final ProblemMapper problemMapper;
    @Autowired
    public ProblemServiceImpl(ProblemMapper problemMapper) {
        this.problemMapper=problemMapper;
    }
    @Override
    public List<Problem> getAllProblem() {
        return list();
    }



    @Override
    public void addPro(Problem problem) {
        String apiID="35645362";
        String apiKey = "vdRRGnLtYDrxHYTExBRQKAAd";
        String secretKey = "ERqMIwpqxOht0Y7GzSv7hGPx8t8KGaPb";
        // 创建SDK客户端

        AipNlp client = new AipNlp(apiID,apiKey, secretKey);

        // 设置可选参数
        HashMap<String, Object> options = new HashMap<>();
        // options.put("key", "value"); // 可选参数
       String probType;
        String title = problem.problemDetail;
        String text = problem.problemDetail;
        JSONObject res = client.keyword(title, text, options);
        JSONArray items = res.getJSONArray("items");
        if (items.length() > 0) {
            JSONObject firstItem = items.getJSONObject(0);
          probType = firstItem.getString("tag");

        } else {
          probType="暂无分类";
        }

        problem.setProblemType(probType);
        // 设置问题状态为待处理
        problem.setProblemState("未处理");
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String dateTimeStr = localDateTime.format(formatter);
        problem.setProblemReportTime(dateTimeStr);
        problemMapper.insert(problem);
    }

    @Override
    public List<Problem> getByUserNo(String userNo) {
        QueryWrapper<Problem> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_no", userNo);
        return baseMapper.selectList(queryWrapper);
    }

    @Override
    public List<Problem> selectByDateRange(String problemReporttime, String problemFinishtime) {
        List<Problem> problems = problemMapper.selectByDateRange(problemReporttime,problemFinishtime);
        System.out.println(problems);
        return problems;
    }

    public boolean deleteFile(String filePath) {
        File file = new File(filePath);
        if (!file.exists() || !file.isFile()) {
            return false;
        }
        boolean result = file.delete();
        return result;
    }

    @Override
    public String gerURLbyID(String id) {
        QueryWrapper<Problem> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("problem_annex").eq("problem_no",id);
        Problem problem = problemMapper.selectOne(queryWrapper);
        System.out.println(problem.getProblemAnnex());
        return  problem.getProblemAnnex();
    }
}
