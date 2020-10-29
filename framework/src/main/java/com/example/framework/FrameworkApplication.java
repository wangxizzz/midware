package com.example.framework;

import com.alibaba.csp.sentinel.init.InitExecutor;
import com.example.framework.component.LearnApplicationContextInitializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class FrameworkApplication {

    public static void main(String[] args) {
        //SpringApplication.run(FrameworkApplication.class, args);
        SpringApplication application = new SpringApplication(FrameworkApplication.class);
        application.addInitializers(new LearnApplicationContextInitializer());

        ConfigurableApplicationContext context = application.run(args);

        // 连接到控制台，与sentinel控制台通信
        System.setProperty("project.name",
                context.getEnvironment().getProperty("spring.application.name","sentinel"));
        System.setProperty("csp.sentinel.dashboard.server",
                context.getEnvironment().getProperty("sentinel.dashboard.server","localhost:8080"));

        InitExecutor.doInit();
    }

}
