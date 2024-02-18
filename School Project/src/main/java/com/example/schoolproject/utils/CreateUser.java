package com.example.schoolproject.utils;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.Console;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

@Component
@AllArgsConstructor
public class CreateUser {

    public HashMap<String, String> getDatabaseData(String[] args) {

        HashMap<String, String> data = new HashMap<>();
        String databaseUrl;
        String databaseUsername;
        String email;

        for (int i = 1; i < args.length; i += 2) {
            if ("--dburl".equals(args[i]) && args[i + 1].equals(System.getenv("DB_URL"))) {
                databaseUrl = args[i + 1];
                data.put("dburl", databaseUrl);
            } else if ("--dbusr".equals(args[i]) && args[i + 1].equals(System.getenv("DB_USERNAME"))) {
                databaseUsername = args[i + 1];
                data.put("--dbusr", databaseUsername);
            } else if ("--email".equals(args[i])) {
                email = args[i + 1];
                data.put("email", email);
            } else {
                System.out.println("Invalid argument: " + args[i]);
            }
        }
        return data;
    }

    public HashMap<String, String> getPassword() {
        Scanner scanner = new Scanner(System.in);
        Console console = System.console();

        String password = "";

        HashMap<String, String> passwordData = new HashMap<>();

        if (console == null) {
            System.out.println("Please enter the password: ");
            password = scanner.nextLine();
            passwordData.put("password", password);
        } else {
            System.out.println("Please enter the password: ");
            password = Arrays.toString(console.readPassword("Please enter the password: "));
        }
        passwordData.put("password", password);
        return passwordData;
    }
}
