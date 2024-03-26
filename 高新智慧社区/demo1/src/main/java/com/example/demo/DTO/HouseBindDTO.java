package com.example.demo.DTO;

import com.example.demo.entity.House;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
public class HouseBindDTO {
    private String carPort;
    private List<House> houses;

}
