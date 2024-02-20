package com.example.schoolproject.Controllers;

import com.example.schoolproject.Entities.SchoolUser;
import com.example.schoolproject.Services.SchoolUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/home")
public class HomeController {

    private SchoolUserService schoolUserService;

    @GetMapping("/trial")
    public ResponseEntity<?> tryConnection(@RequestParam Long id) {
        HashMap<String, String> response = new HashMap<>();
        Optional<SchoolUser> selectedUser = schoolUserService.findUserById(id);
        response.put("hello", selectedUser.get().getUserName());
        return ResponseEntity.ok(response);
    }
}
