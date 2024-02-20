package com.example.schoolproject.Services;

import com.example.schoolproject.Entities.SchoolUser;

import java.util.Optional;

public interface SchoolUserService {

    SchoolUser saveFirstUser(String email, String password);

    Optional<SchoolUser> findUserById(Long id);
}
