package com.sandbox.domain;

import org.hibernate.annotations.Comment;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

@SpringBootApplication
public class SprinklerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SprinklerApplication.class, args);
    }

}
