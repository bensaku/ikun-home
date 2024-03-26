package com.example.demo.DTO;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class IdentityDTO {
    private String userNo;
    private String Name;
    private String Sex;
    private String Nation;
    private String Addr;
    private String Id;
    private String Duetime;

}
