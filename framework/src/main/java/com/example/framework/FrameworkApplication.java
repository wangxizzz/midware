package com.example.framework;

import com.example.framework.component.LearnApplicationContextInitializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FrameworkApplication {

    public static void main(String[] args) {
        //SpringApplication.run(FrameworkApplication.class, args);
        SpringApplication application = new SpringApplication(FrameworkApplication.class);
        application.addInitializers(new LearnApplicationContextInitializer());

        application.run(args);
    }

}
