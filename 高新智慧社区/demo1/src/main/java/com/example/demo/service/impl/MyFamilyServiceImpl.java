package com.example.demo.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.entity.MyFamily;
import com.example.demo.mapper.MyFamilyMapper;
import com.example.demo.service.MyFamilyService;
import org.springframework.stereotype.Service;
import java.util.Optional;

import java.util.List;

@Service
public class   MyFamilyServiceImpl extends ServiceImpl<MyFamilyMapper, MyFamily> implements MyFamilyService {


    @Override
    public List<MyFamily> getByUserNo(String userNo) {
        QueryWrapper<MyFamily> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_no", userNo);
        return baseMapper.selectList(queryWrapper);
    }


    @Override
    public Optional<String> getUserNo(String famNo){
        QueryWrapper<MyFamily> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("fam_No", famNo);
        MyFamily myFamily = baseMapper.selectOne(queryWrapper);
        return Optional.ofNullable(myFamily)
                .map(MyFamily::getUserNo);
    }


    @Override
    public List<MyFamily> getByFamNo(String famNo) {
        Optional<String> userNoOptional = getUserNo(famNo);
        String userNo = null;
        if (userNoOptional.isPresent()) {
            userNo = userNoOptional.get();
            // 执行需要返回值的操作
        }
        QueryWrapper<MyFamily> queryWrapper2 = new QueryWrapper<>();
        queryWrapper2.eq("user_no", userNo);
        return baseMapper.selectList(queryWrapper2);
    }
}
