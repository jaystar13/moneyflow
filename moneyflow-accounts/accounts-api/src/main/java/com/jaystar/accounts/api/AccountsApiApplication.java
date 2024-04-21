package com.jaystar.accounts.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.jaystar.accounts")
public class AccountsApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountsApiApplication.class, args);
    }
}
