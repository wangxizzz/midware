package com.example.framework;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.annotation.Order;

/**
 * @author wangxi created on 2020/10/21 22:48
 * @version v1.0
 */
@Order(100)
public class LearnApplicationContextInitializer implements ApplicationContextInitializer {
    @Override
    public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
        System.out.println("容器初始化的bean数量为 : " +
                configurableApplicationContext.getBeanDefinitionCount());
    }
}
