package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.entity.Family;
import com.example.demo.mapper.FamilyMapper;
import com.example.demo.service.FamilyService;
import org.springframework.stereotype.Service;

@Service
public class FamilyServiceImpl extends ServiceImpl<FamilyMapper, Family> implements FamilyService {


    @Override
    public void addFam(Family family) {
        baseMapper.insert(family);
    }

    @Override
    public void deleteFam(String famId) {
        QueryWrapper<Family> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("fam_id", famId);
        baseMapper.delete(queryWrapper);
    }

    @Override
    public boolean realname(String id) {
        return getById(id) != null;
    }
}
