package com.example.schoolproject;

import com.example.schoolproject.Repositories.SchoolUserRepository;
import com.example.schoolproject.Services.SchoolUserService;
import com.example.schoolproject.utils.CreateUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;
import java.util.Objects;

@SpringBootApplication
public class SchoolProjectApplication implements CommandLineRunner {

    private final SchoolUserService schoolUserService;
    private final SchoolUserRepository schoolUserRepository;

    @Autowired
    public SchoolProjectApplication(SchoolUserService schoolUserService, SchoolUserRepository schoolUserRepository) {
        this.schoolUserService = schoolUserService;
        this.schoolUserRepository = schoolUserRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(SchoolProjectApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        CreateUser createUser = new CreateUser();

        if (Objects.equals(args[0], "--innit")) {
            if (args.length != 7) {
                System.out.println("Missing --innit keyword.");
            }

            HashMap<String, String> databaseData = createUser.getDatabaseData(args);
            HashMap<String, String> getUserPassword = createUser.getPassword();

            schoolUserService.saveFirstUser(databaseData.get("email"), getUserPassword.get("password"));
            System.out.println("User created.");
        }
    }

}
