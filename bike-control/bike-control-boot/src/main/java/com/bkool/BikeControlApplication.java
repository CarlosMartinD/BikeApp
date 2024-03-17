package com.bkool;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication(exclude= {UserDetailsServiceAutoConfiguration.class})
public class BikeControlApplication {

    public static void main(String[] args) {
        SpringApplication.run(BikeControlApplication.class, args);
    }

}
