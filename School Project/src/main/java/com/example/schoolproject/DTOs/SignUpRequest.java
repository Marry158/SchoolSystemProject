package com.example.schoolproject.DTOs;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class SignUpRequest {
    private String userName;
    private String password;
}
