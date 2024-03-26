package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.DTO.IdentityDTO;
import com.example.demo.entity.Identity;


public interface RealNameService extends IService<Identity> {
    boolean realname(IdentityDTO identityDTO);
}
