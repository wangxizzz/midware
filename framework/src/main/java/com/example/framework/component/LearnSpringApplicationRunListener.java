package com.example.framework.component;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringApplicationRunListener;

/**
 * @author wangxi03 created on 2020/10/22 1:12 PM
 * @version v1.0
 */
public class LearnSpringApplicationRunListener implements SpringApplicationRunListener {

    public LearnSpringApplicationRunListener(SpringApplication application, String[] args) {
        System.out.println("LearnSpringApplicationRunListener构造函数被调用");

    }

    @Override
    public void starting() {
        System.out.println("LearnSpringApplicationRunListener starting......");
    }
}
