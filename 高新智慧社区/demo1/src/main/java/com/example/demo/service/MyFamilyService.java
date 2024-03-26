package com.example.demo.service;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.entity.MyFamily;

import java.util.List;
import java.util.Optional;

public interface MyFamilyService extends IService<MyFamily> {
    List<MyFamily> getByUserNo(String userNo);

    Optional<String> getUserNo(String famNo);

    List<MyFamily> getByFamNo(String famNo);
}
