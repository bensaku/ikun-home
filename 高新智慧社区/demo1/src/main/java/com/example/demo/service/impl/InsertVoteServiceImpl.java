package com.example.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.DTO.VoteDTO;
import com.example.demo.entity.Vote;
import com.example.demo.mapper.VoteMapper;
import com.example.demo.service.InsertVoteService;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;


@Service
public class InsertVoteServiceImpl extends ServiceImpl<VoteMapper, Vote> implements InsertVoteService {


    @Override
    public boolean InsertVoteBool(VoteDTO voteDTO) throws ParseException {
        String voteName = voteDTO.getVoteName();
       Integer voteExpend = voteDTO.getVoteExpend();
        String voteEndtime = voteDTO.getVoteEndtime();
        String voteDetail = voteDTO.getVoteDetail();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date2 = dateFormat.parse(voteEndtime);
        LocalDate currentDate = LocalDate.now();
        Date date1 = Date.from(currentDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        int comparisonResult = date1.compareTo(date2);
        System.out.println(comparisonResult);
        if(comparisonResult>=0) {return false;}
        Vote vote = new Vote();
        vote.setVoteName(voteName);
        vote.setVoteDetail(voteDetail);
        vote.setVoteEndtime(voteEndtime);
        vote.setVoteExpend(voteExpend);
        vote.setVoteStarttime(String.valueOf(currentDate));
        vote.setVoteResult("进行");
        vote.setVoteAgainst(0);
        vote.setVoteSupport(0);
        boolean saved;
        saved = save(vote);

        return saved;
    }
}
