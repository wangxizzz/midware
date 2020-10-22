package com.example.framework.component;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * @author wangxi created on 2020/10/21 23:00
 * @version v1.0
 *
 * 在具体的实战业务中，我们也可以自定义事件，在完成业务之后手动触发对应的事件监听器，
 * 也就是手动调用ApplicationContext的publishEvent(ApplicationEvent event)方法
 */
@Component
public class LearnApplicationListener implements ApplicationListener<ContextRefreshedEvent> {
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        System.out.println("监听器获取容器初始化bean的数量: " + event.getApplicationContext().getBeanDefinitionCount());
    }
}
