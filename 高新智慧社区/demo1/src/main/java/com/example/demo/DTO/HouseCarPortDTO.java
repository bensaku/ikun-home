package com.example.demo.DTO;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
public class HouseCarPortDTO {
    private String carPort;
    private String carNo;
    private String carDetail;
    private String carCharge;
    private String carTime;
    private String carPosition;
    private List<Integer> houseNo;
    private List<String> house;
}
