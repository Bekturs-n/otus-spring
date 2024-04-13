package com.mvc.classic;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class);
        System.out.println("Application is running on \n url:http://localhost:8080/");
    }
}
