package com.example.schoolproject.utils;

import org.springframework.security.crypto.password.PasswordEncoder;

public class CustomPasswordEncoder implements PasswordEncoder {

    private final PasswordUtil passwordUtil = new PasswordUtil();


    @Override
    public String encode(CharSequence rawPassword) {
        try {
            return passwordUtil.hashPassword(rawPassword.toString());
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        try {
            return passwordUtil.validatePassword(rawPassword.toString(), encodedPassword.toString());
        } catch (Exception e) {
            throw new RuntimeException("Error validating password", e);
        }
    }
}
