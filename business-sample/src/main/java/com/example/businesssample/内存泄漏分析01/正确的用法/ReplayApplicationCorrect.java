package com.example.businesssample.内存泄漏分析01.正确的用法;

import java.util.List;

/**
 * @Author wangxi
 * @Time 2020/5/6 18:10
 */
public class ReplayApplicationCorrect {
    public static void main(String[] args) throws InterruptedException {

        ReplayWithoutProblemCorrect replay2 = new ReplayWithoutProblemCorrect();
        List<String> cache2 = replay2.loadMockRequest(10000);
        replay2.start(cache2);

    }
}
