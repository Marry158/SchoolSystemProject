package com.example.schoolproject.Services;

import com.example.schoolproject.Entities.SchoolUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

;

@Service
@RequiredArgsConstructor
public class CustomUserDetails implements UserDetailsService {

    private SchoolUserService schoolUserService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Optional<SchoolUser> user = schoolUserService.findUserByEmail(email);

        UserDetails userDetails = org.springframework.security.core.userdetails.User.builder()
                .username(user.get().getUserName())
                .password(user.get().getPassword())
                .build();

        return userDetails;
    }

}
