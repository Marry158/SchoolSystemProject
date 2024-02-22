package com.example.schoolproject.Controllers;

import com.example.schoolproject.DTOs.SignInRequest;
import com.example.schoolproject.DTOs.SignUpRequest;
import com.example.schoolproject.Entities.SchoolUser;
import com.example.schoolproject.Services.AuthenticationService;
import com.example.schoolproject.Services.SchoolUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final SchoolUserService schoolUserService;


    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignUpRequest request) throws NoSuchAlgorithmException, InvalidKeySpecException {
        authenticationService.signUp(request);

        return ResponseEntity.ok("User was created.");
    }

    @PostMapping("/signin")
    public ResponseEntity<?> signIn(@RequestBody SignInRequest request) throws NoSuchAlgorithmException, InvalidKeySpecException {

        if (request.getUserName() == null || request.getUserName().isEmpty()) {
            throw new RuntimeException("Email is empty.");
        } else if (request.getPassword() == null || request.getPassword().isEmpty()) {
            throw new RuntimeException("Password is empty.");
        }

        Optional<SchoolUser> selectedUser = schoolUserService.findUserByEmail(request.getUserName());
        if (selectedUser.isEmpty()) {
            throw new RuntimeException("User does not exist.");
        }

        return ResponseEntity.ok(authenticationService.signIn(request));
    }
}
