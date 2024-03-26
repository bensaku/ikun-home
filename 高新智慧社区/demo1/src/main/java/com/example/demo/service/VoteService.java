package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;

import com.example.demo.entity.Vote;

import java.util.List;

public interface VoteService extends IService<Vote> {
    List<Vote> getAllVote();

    void deleteVote(String voteNo);


    void updateVoteState(String voteNo, String voteType);

    List<Vote> selectByDateRange(String voteStarttime, String voteEndtime);

    void cancelVoteState(String voteNo, String voteType);
}
