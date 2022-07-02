package com.nhnacademy.edu.springboot.hazelcastex1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.util.Scanner;

@SpringBootApplication
public class HazelcastApplication {

    public static void main(String[] args) {
        SpringApplication.run(HazelcastApplication.class, args);
    }

}
