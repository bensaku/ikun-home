package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.Vote;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface VoteMapper extends BaseMapper<Vote> {
    @Delete("DELETE FROM vote WHERE vote_no = #{voteNo}")
    int deleteVote(@Param("voteNo") String voteNo);
    @Select("SELECT * FROM vote WHERE vote_starttime BETWEEN STR_TO_DATE(#{voteStarttime}, '%Y-%m-%d') AND STR_TO_DATE(#{voteEndtime}, '%Y-%m-%d')")
    List<Vote> selectByDateRange(@Param("voteStarttime") String voteStarttime, @Param("voteEndtime") String voteEndtime);
    }

