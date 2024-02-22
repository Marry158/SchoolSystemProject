package com.example.schoolproject.Services;

import com.example.schoolproject.Entities.SchoolUser;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Optional;

public interface SchoolUserService {

    SchoolUser saveFirstUser(String email, String password) throws NoSuchAlgorithmException, InvalidKeySpecException;

    Optional<SchoolUser> findUserById(Long id);

    Optional<SchoolUser> findUserByEmail(String email);
}
