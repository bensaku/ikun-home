package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.VoteUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface VoteUserMapper extends BaseMapper<VoteUser> {
}
