package com.example.framework.aop;

import com.example.framework.BaseTest;
import org.junit.Test;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author wangxi03 created on 2020/11/25  11:30 上午
 * @version v1.0
 */
// 必须加pulic否则，test运行不了
public class AopConfigTest extends BaseTest {
    @Resource
    private OrderService orderService;

    @Test
    public void test01() {
        orderService.createOrder("tom", "饭");
    }
}