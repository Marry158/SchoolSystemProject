package com.example.schoolproject.Services;

import com.example.schoolproject.DTOs.JWTAuthResponse;
import com.example.schoolproject.DTOs.SignInRequest;
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

    private final JWTUtil jwtUtil;
    private final PasswordUtil passwordUtil;
    private final SchoolUserRepository schoolUserRepository;


    @Override
    public void signUp(SignUpRequest signUpRequest) throws NoSuchAlgorithmException, InvalidKeySpecException {
        String hashedPassword = passwordUtil.hashPassword(signUpRequest.getPassword());

        SchoolUser user = new SchoolUser();
        user.setUserName(signUpRequest.getUserName());
        user.setPassword(hashedPassword);
        schoolUserRepository.save(user);

    }

    @Override
    public JWTAuthResponse signIn(SignInRequest signInRequest) throws NoSuchAlgorithmException, InvalidKeySpecException {
        var user = schoolUserRepository.findByUserName(signInRequest.getUserName());

        if (!passwordUtil.validatePassword(signInRequest.getPassword(), user.get().getPassword())) {
            throw new RuntimeException("Invalid password.");
        }

        String jwt = jwtUtil.createToken(user.get());
        JWTAuthResponse jwtAuthResponse = new JWTAuthResponse();
        jwtAuthResponse.setToken(jwt);
        return jwtAuthResponse;
    }
}
