package com.example.framework.aop;

import org.springframework.stereotype.Component;

/**
 * @author wangxi03 created on 2020/11/25  10:57 上午
 * @version v1.0
 */
@Component
public class OrderServiceImpl implements OrderService{
    @Override
    public AopOrderEntity createOrder(String username, String product) {
        AopOrderEntity order = new AopOrderEntity();
        order.setUsername(username);
        order.setProduct(product);
        return order;
    }

    @Override
    public AopOrderEntity queryOrder(String username) {
        AopOrderEntity order = new AopOrderEntity();
        order.setUsername("test");
        order.setProduct("test");
        return order;
    }
}
