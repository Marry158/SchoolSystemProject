package com.example.schoolproject.Controllers;

import com.example.schoolproject.DTOs.SignUpRequest;
import com.example.schoolproject.Services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;


    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignUpRequest request) throws NoSuchAlgorithmException, InvalidKeySpecException {
        authenticationService.signUp(request);

        return ResponseEntity.ok("User was created.");
    }
}
