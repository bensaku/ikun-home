package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.Complaint;
import com.example.demo.entity.Problem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ProblemMapper extends BaseMapper<Problem> {
    @Select("SELECT * FROM problem WHERE problem_reporttime BETWEEN STR_TO_DATE(#{problemReporttime}, '%Y-%m-%d') AND STR_TO_DATE(#{problemFinishtime}, '%Y-%m-%d')")
    List<Problem> selectByDateRange(@Param("problemReporttime") String problemReporttime, @Param("problemFinishtime") String problemFinishtime);
}
