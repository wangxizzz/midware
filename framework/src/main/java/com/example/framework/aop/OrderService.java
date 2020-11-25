package com.example.framework.aop;

/**
 * @author wangxi03 created on 2020/11/25  10:55 上午
 * @version v1.0
 */
public interface OrderService {
    AopOrderEntity createOrder(String username, String product);

    AopOrderEntity queryOrder(String username);
}
