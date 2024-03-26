package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.entity.Vote;
import com.example.demo.mapper.VoteMapper;
import com.example.demo.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class VoteServiceImpl extends ServiceImpl<VoteMapper, Vote> implements VoteService {
    private final  VoteMapper voteMapper;
    @Autowired
    public VoteServiceImpl(VoteMapper voteMapper) {
        this.voteMapper = voteMapper;
    }
    @Override
    public List<Vote> getAllVote() {
        return list();
    }

    @Override
    public void updateVoteState(String voteNo, String voteType) { // 修改vote表中的 支持数据
        // 获取到voteNo对应的元组的属性
        Vote vote = getById(voteNo);
        int support = vote.getVoteSupport();
        int against = vote.getVoteAgainst();


        UpdateWrapper<Vote> voteUpdateWrapper = new UpdateWrapper<>();
        if (Objects.equals(voteType, "支持")){
            voteUpdateWrapper.eq("vote_no", voteNo)
                    .set("vote_support", support + 1);
            update(voteUpdateWrapper);
        }
        else if (Objects.equals(voteType, "反对")) {
            voteUpdateWrapper.eq("vote_no", voteNo)
                    .set("vote_against", against + 1);
            update(voteUpdateWrapper);
        }
    }

    @Override
    public void cancelVoteState(String voteNo, String voteType) {
        // 获取到voteNo对应的元组的属性
        Vote vote = getById(voteNo);
        int support = vote.getVoteSupport();
        int against = vote.getVoteAgainst();

        UpdateWrapper<Vote> voteUpdateWrapper = new UpdateWrapper<>();
        if (Objects.equals(voteType, "支持")){
            voteUpdateWrapper.eq("vote_no", voteNo)
                    .set("vote_support", support - 1);
            update(voteUpdateWrapper);
        }
        else if (Objects.equals(voteType, "反对")) {
            voteUpdateWrapper.eq("vote_no", voteNo)
                    .set("vote_against", against - 1);
            update(voteUpdateWrapper);
        }
    }

    @Override
    public void deleteVote(String voteNo) {
        voteMapper.deleteVote(voteNo);
    }

    @Override
    public List<Vote> selectByDateRange(String voteStarttime, String voteEndtime) {
        List<Vote> votes = voteMapper.selectByDateRange(voteStarttime,voteEndtime);
        System.out.println(votes);
        return votes;
    }
}
