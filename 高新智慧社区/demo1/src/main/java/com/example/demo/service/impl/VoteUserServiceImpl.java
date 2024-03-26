package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.AbstractWrapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.entity.VoteUser;
import com.example.demo.mapper.VoteUserMapper;
import com.example.demo.service.VoteUserService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class VoteUserServiceImpl extends ServiceImpl<VoteUserMapper, VoteUser> implements VoteUserService {

    @Override
    public boolean isVoted(String userNo, String voteNo) {
        QueryWrapper<VoteUser> voteUserQueryWrapper = new QueryWrapper<>();
        voteUserQueryWrapper.eq("user_no", userNo)
                .eq("vote_no", voteNo);
        return count(voteUserQueryWrapper) == 1;
    }

    @Override
    public void addVoteUser(String userNo, String voteNo, String voteType) { // 在vote_user表中添加一条记录
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = currentDate.format(formatter);
        System.out.println(formattedDate);

        // 构建对象
        VoteUser voteUser = new VoteUser();
        voteUser.setUserNo(userNo);
        voteUser.setVoteNo(voteNo);
        voteUser.setVoteTime(formattedDate);
        voteUser.setVoteState(voteType);

        // 将实例插入
        save(voteUser);

    }

    @Override
    public List<VoteUser> getVoteUserByUserNo(String userNo) {
        QueryWrapper<VoteUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_no", userNo);
        return list(queryWrapper);
    }

    @Override
    public void cancelVoteUser(String userNo, String voteNo) {
        QueryWrapper<VoteUser> voteUserQueryWrapper = new QueryWrapper<>();
        voteUserQueryWrapper.eq("user_no", userNo)
                .eq("vote_no", voteNo);
        remove(voteUserQueryWrapper);
    }
}
