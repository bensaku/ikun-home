package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("vote")
public class Vote {

    @TableId(value = "vote_no", type = IdType.AUTO)
    public  String voteNo;
    private String voteName;
    private String voteDetail;
    private Integer voteSupport;
    private Integer voteAgainst;
    private String voteResult;
    private Integer voteExpend;
    private String voteStarttime;
    private String voteEndtime;
}
