package com.example.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.DTO.IdentityDTO;
import com.example.demo.entity.Identity;
import com.example.demo.entity.User;
import com.example.demo.mapper.IdentityMapper;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.RealNameService;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Service
public class RealNameServiceImpl extends ServiceImpl<IdentityMapper, Identity> implements RealNameService {
    @Override
    public boolean realname(IdentityDTO identityDTO) {
        String name = identityDTO.getName();
        String sex = identityDTO.getSex();
        String nation = identityDTO.getNation();
        String addr = identityDTO.getAddr();
        String id = identityDTO.getId();
        String duetime = identityDTO.getDuetime();

        if (name == null || sex == null || nation == null || addr == null || id == null || duetime == null) {
            return false;
        }

        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = currentDate.format(formatter);

        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date1 = dateFormat.parse(duetime);
            Date date2 = dateFormat.parse(formattedDate);

            int comparisonResult = date1.compareTo(date2);

            if (comparisonResult <= 0) {
                return false;
            } else {
                Identity identity = new Identity();
                identity.setName(name);
                identity.setSex(sex);
                identity.setNation(nation);
                identity.setAddress(addr);
                identity.setId(id);
                identity.setBornDate(id.substring(6, 10) + "-" + id.substring(10, 12) + "-" + id.substring(12, 14));
                identity.setDuetime(duetime.substring(0, 10));
                return save(identity);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        
        return false;
    }
}
