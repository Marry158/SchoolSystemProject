package com.example.schoolproject.Services;

import com.example.schoolproject.Entities.SchoolUser;
import com.example.schoolproject.Repositories.SchoolUserRepository;
import com.example.schoolproject.utils.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Optional;

@Service
public class SchoolUserServiceImpl implements SchoolUserService {

    private final SchoolUserRepository schoolUserRepository;
    private final PasswordUtil passwordUtil;

    @Autowired
    public SchoolUserServiceImpl(SchoolUserRepository schoolUserRepository, PasswordUtil passwordUtil) {
        this.schoolUserRepository = schoolUserRepository;
        this.passwordUtil = passwordUtil;
    }

    @Override
    public SchoolUser saveFirstUser(String email, String password) throws NoSuchAlgorithmException, InvalidKeySpecException {
        SchoolUser user = SchoolUser.builder()
                .userName(email)
                .password(passwordUtil.hashPassword(password))
                .build();

        schoolUserRepository.save(user);
        return user;
    }

    @Override
    public Optional<SchoolUser> findUserById(Long id) {
        return schoolUserRepository.findById(id);
    }

    @Override
    public Optional<SchoolUser> findUserByEmail(String email) {
        return schoolUserRepository.findByUserName(email);
    }
}
