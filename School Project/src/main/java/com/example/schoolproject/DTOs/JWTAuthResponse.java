package com.example.schoolproject.DTOs;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class JWTAuthResponse {
    private String token;
}
