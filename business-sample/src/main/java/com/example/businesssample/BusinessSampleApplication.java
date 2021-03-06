package com.example.businesssample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class BusinessSampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(BusinessSampleApplication.class, args);
    }

}
