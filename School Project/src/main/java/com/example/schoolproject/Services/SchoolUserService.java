package com.example.schoolproject.Services;

import com.example.schoolproject.Entities.SchoolUser;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Optional;

public interface SchoolUserService {

    UserDetailsService userDetailsService();

    SchoolUser saveFirstUser(String email, String password) throws NoSuchAlgorithmException, InvalidKeySpecException;

    Optional<SchoolUser> findUserById(Long id);

    Optional<SchoolUser> findUserByEmail(String email);
}
