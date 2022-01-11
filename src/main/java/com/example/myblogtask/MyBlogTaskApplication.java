package com.example.myblogtask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class MyBlogTaskApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyBlogTaskApplication.class, args);
    }

}
