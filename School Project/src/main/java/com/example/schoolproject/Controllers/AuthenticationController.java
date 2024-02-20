package com.example.schoolproject.Controllers;

import com.example.schoolproject.Services.SchoolUserService;
import com.example.schoolproject.utils.PasswordUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthenticationController {
    private PasswordUtil passwordUtil;
    private SchoolUserService schoolUserService;


//    @PostMapping("/signup")
//    public ResponseEntity<?> signup(@RequestBody SignUpRequest request) {
//
//    }
}
