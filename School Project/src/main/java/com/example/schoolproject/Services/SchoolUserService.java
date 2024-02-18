package com.example.schoolproject.Services;

import com.example.schoolproject.Entities.SchoolUser;

public interface SchoolUserService {

    SchoolUser saveFirstUser(String email, String password);
}
