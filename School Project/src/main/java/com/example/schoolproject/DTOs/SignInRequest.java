package com.example.schoolproject.DTOs;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class SignInRequest {

    private String userName;
    private String password;
}
