package com.example.demo.controller;

import com.example.demo.DTO.VoteDTO;
import com.example.demo.entity.Vote;
import com.example.demo.service.InsertVoteService;
import com.example.demo.service.VoteService;
import com.example.demo.service.VoteUserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/api/vote")
public class VoteController {
    @Resource
    private VoteService voteService;

    @Resource
    private InsertVoteService insertVoteService;
    @GetMapping("/getVotes")
    public List<Vote> getVotes() {
        // 调用MyBatis Plus的查询方法来获取问题列表
        return voteService.getAllVote();
    }
    @PostMapping ("/insertVote")
    public String insertVotes(@RequestBody VoteDTO voteDTO) throws ParseException {
        System.out.println(voteDTO);
        if(insertVoteService.InsertVoteBool(voteDTO))
        {return "插入成功";}
        else {return "截止日期需在当前时间之后";}
}

    @Resource
    private VoteUserService voteUserService;

    @RequestMapping("/doVote/{userNo}/{voteNo}/{voteType}")
    public String doVote(@PathVariable("userNo") String userNo,
                         @PathVariable("voteNo") String voteNo,
                         @PathVariable("voteType") String voteType){ //传入参数：用户编号userNo、投票编号voteNo、投票状态（支持、反对）

        System.out.println(userNo);
        System.out.println(voteNo);
        System.out.println(voteNo);

        // 首先判断：该用户是否参与过该投票？
        if (voteUserService.isVoted(userNo, voteNo)) { // 是：则返回：“已参与”
            return "已参与";
        }
        else { // 否：进行投票流程：
            voteService.updateVoteState(voteNo, voteType); // 1、在Vote表中修改Support或Oppose
            voteUserService.addVoteUser(userNo, voteNo, voteType); // 2、在VoteUser表中
            return "投票成功";
        }
    }

    @PostMapping("/cancelVote/{userNo}/{voteNo}/{voteType}")
    public String cancelVote(@PathVariable("userNo") String userNo,
                             @PathVariable("voteNo") String voteNo,
                             @PathVariable("voteType") String voteType){
        voteService.cancelVoteState(voteNo, voteType); // 根据voteType决定减少哪一种属性的数量
        voteUserService.cancelVoteUser(userNo, voteNo); // 根据userNo 和 voteNo 删除记录
        return "取消投票成功";
    }

    @DeleteMapping("/deleteVote/{voteNo}")
    public void deleteVote(@PathVariable String voteNo) {
      voteService.deleteVote(voteNo);
  }


    @GetMapping("/getVotesByDateRange")
        public List<Vote> selectByDateRange(@RequestParam String voteStarttime,@RequestParam String voteEndtime) {
        System.out.println(1);
        return voteService.selectByDateRange(voteStarttime,voteEndtime);
        }
}
