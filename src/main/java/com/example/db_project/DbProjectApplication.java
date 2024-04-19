package com.example.db_project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DbProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(DbProjectApplication.class, args);
    }

}
