package com.example.schoolproject.Controllers;

import com.example.schoolproject.Services.SchoolUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/home")
public class HomeController {

    private SchoolUserService schoolUserService;

    @GetMapping("/trial")
    public ResponseEntity<?> tryConnection() {

        return ResponseEntity.ok("hello world");
    }
}
