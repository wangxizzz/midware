package com.example.framework.aop;

import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.aop.support.RegexpMethodPointcutAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * @author wangxi03 created on 2020/11/25  11:03 上午
 * @version v1.0
 *
 * https://juejin.cn/post/6844903698221514765
 *
 * https://blog.csdn.net/f641385712/article/details/88925478
 */
@Configuration
public class AopConfig {

    @Resource
    private LogArgsAdvice logArgsAdvice;

    @Resource
    private LogResultAdvice logResultAdvice;

    @Bean
    public RegexpMethodPointcutAdvisor logArgsAdvisor() {
        RegexpMethodPointcutAdvisor advisor = new RegexpMethodPointcutAdvisor();
        advisor.setAdvice(logArgsAdvice);
        // 拦截aop包下的所有子包 的所有类与所有方法
//        advisor.setPattern("com.example.framework.aop..*.*(..)");
        // 拦截OrderService下所有方法
        advisor.setPattern("com.example.framework.aop.OrderService.*(..)");
        return advisor;
    }

    @Bean
    public RegexpMethodPointcutAdvisor logResultAdvisor() {
        RegexpMethodPointcutAdvisor advisor = new RegexpMethodPointcutAdvisor();
        advisor.setAdvice(logResultAdvice);
        // 拦截aop包下的所有子包 的所有类与所有方法
//        advisor.setPattern("com.example.framework.aop..*.*(..)");
        // 拦截OrderService下所有方法
        advisor.setPattern("com.example.framework.aop.OrderService.*(..)");
        return advisor;
    }

//    @Bean
//    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
//        return new DefaultAdvisorAutoProxyCreator();
//    }
}
