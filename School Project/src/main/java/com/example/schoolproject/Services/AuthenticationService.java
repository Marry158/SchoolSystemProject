package com.example.schoolproject.Services;

import com.example.schoolproject.DTOs.JWTAuthResponse;
import com.example.schoolproject.DTOs.SignInRequest;
import com.example.schoolproject.DTOs.SignUpRequest;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public interface AuthenticationService {

    void signUp(SignUpRequest signUpRequest) throws NoSuchAlgorithmException, InvalidKeySpecException;

    JWTAuthResponse signIn(SignInRequest signInRequest) throws NoSuchAlgorithmException, InvalidKeySpecException;
}
