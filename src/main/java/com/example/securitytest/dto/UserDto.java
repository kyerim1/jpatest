package com.example.securitytest.dto;

import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private String userId;
    private String userPw;
}
