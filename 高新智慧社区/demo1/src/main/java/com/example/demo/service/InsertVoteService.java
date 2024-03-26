package com.example.demo.service;

import com.example.demo.DTO.VoteDTO;

import java.text.ParseException;

public interface InsertVoteService {

    boolean InsertVoteBool(VoteDTO voteDTO) throws ParseException;
}
