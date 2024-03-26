package com.example.demo.DTO;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class RetrieveDTO {
    private String userNo;

    private String userPassword;

    private String code;
}