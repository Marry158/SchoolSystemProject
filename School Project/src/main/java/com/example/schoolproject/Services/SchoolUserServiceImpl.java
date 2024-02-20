package com.example.schoolproject.Services;

import com.example.schoolproject.Entities.SchoolUser;
import com.example.schoolproject.Repositories.SchoolUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SchoolUserServiceImpl implements SchoolUserService {

    private final SchoolUserRepository schoolUserRepository;

    @Autowired
    public SchoolUserServiceImpl(SchoolUserRepository schoolUserRepository) {
        this.schoolUserRepository = schoolUserRepository;
    }

    @Override
    public SchoolUser saveFirstUser(String email, String password) {
        SchoolUser user = SchoolUser.builder()
                .userName(email)
                .password(password)
                .build();

        schoolUserRepository.save(user);
        return user;
    }

    @Override
    public Optional<SchoolUser> findUserById(Long id) {
        return schoolUserRepository.findById(id);
    }
}
