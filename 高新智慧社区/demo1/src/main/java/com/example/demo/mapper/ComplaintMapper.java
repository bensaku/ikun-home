package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.Complaint;
import com.example.demo.entity.Vote;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ComplaintMapper extends BaseMapper<Complaint> {
    @Select("SELECT * FROM complaint WHERE complaint_reporttime BETWEEN STR_TO_DATE(#{complaintReporttime}, '%Y-%m-%d') AND STR_TO_DATE(#{complaintFinishtime}, '%Y-%m-%d')")
    List<Complaint> selectByDateRange(@Param("complaintReporttime") String complaintReporttime, @Param("complaintFinishtime") String complaintFinishtime);
}
