package com.example.demo.service;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.entity.Family;

public interface FamilyService extends IService<Family> {

    void addFam(Family family);

    void deleteFam(String famId);

    boolean realname(String id);
}
