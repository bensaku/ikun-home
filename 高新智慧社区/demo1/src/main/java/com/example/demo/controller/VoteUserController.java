package com.example.demo.controller;


import com.example.demo.entity.VoteUser;
import com.example.demo.service.VoteUserService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/voteUser")
public class VoteUserController {
    @Resource
    VoteUserService voteUserService;
    // 通过userNo来获取元组
    @RequestMapping("/doVote/{userNo}")
    public List<VoteUser> getVoteUserByUserNo(@PathVariable("userNo") String userNo){
        return voteUserService.getVoteUserByUserNo(userNo);
    }
}
