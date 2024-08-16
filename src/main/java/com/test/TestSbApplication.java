package com.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@SpringBootApplication
@Component
public class TestSbApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestSbApplication.class, args);
    }

}
