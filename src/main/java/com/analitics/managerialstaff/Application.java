package com.analitics.managerialstaff;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author by nikolai.pashkevich
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.analitics.managerialstaff")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
