package com.example.schoolproject.Services;

import com.example.schoolproject.DTOs.SignUpRequest;
import com.example.schoolproject.Entities.SchoolUser;
import com.example.schoolproject.Repositories.SchoolUserRepository;
import com.example.schoolproject.utils.JWTUtil;
import com.example.schoolproject.utils.PasswordUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private JWTUtil jwtUtil;
    private PasswordUtil passwordUtil;
    private SchoolUserRepository schoolUserRepository;


    @Override
    public void signUp(SignUpRequest signUpRequest) throws NoSuchAlgorithmException, InvalidKeySpecException {
        String hashedPassword = passwordUtil.hashPassword(signUpRequest.getPassword());

        SchoolUser user = new SchoolUser();
        user.setUserName(signUpRequest.getUserName());
        user.setPassword(hashedPassword);
        schoolUserRepository.save(user);

    }
}
