package com.example.demo.DTO;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class UserLoginDTO {
    private String userNo;
    private String userPassword;

    public String getUserNo() {
        return this.userNo;
    }
    public String getUserPassword() {
        return this.userPassword;
    }
}

