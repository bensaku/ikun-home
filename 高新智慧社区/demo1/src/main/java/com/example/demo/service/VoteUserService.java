package com.example.demo.service;

import com.example.demo.entity.VoteUser;

import java.util.List;

public interface VoteUserService {
    boolean isVoted(String userNo, String voteNo);

    void addVoteUser(String userNo, String voteNo, String voteType);

    List<VoteUser> getVoteUserByUserNo(String userNo);

    void cancelVoteUser(String userNo, String voteNo);
}
