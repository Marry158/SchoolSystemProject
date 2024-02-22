package com.example.schoolproject.Services;

import com.example.schoolproject.DTOs.SignUpRequest;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public interface AuthenticationService {

    void signUp(SignUpRequest signUpRequest) throws NoSuchAlgorithmException, InvalidKeySpecException;
}
