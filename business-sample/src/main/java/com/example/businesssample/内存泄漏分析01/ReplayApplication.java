package com.example.businesssample.内存泄漏分析01;

import org.apache.http.client.methods.HttpUriRequest;

import java.util.List;

/**
 * @Author wangxi
 * @Time 2020/5/6 17:11
 *
 * Demo 的主要逻辑是这样的，首先创建一个缓存列表，用来保存需要发送的请求数据。
 *
 * 然后，通过循环的方式从缓存列表中取出需要发送的请求，将其交由 httpasyncclient 客户端进行发送。
 */
public class ReplayApplication {

    public static void main(String[] args) throws InterruptedException {

        //创建有内存泄露的回放客户端
        ReplayWithProblem replay1 = new ReplayWithProblem();

        //加载一万条请求数据放入缓存
        List<HttpUriRequest> cache1 = replay1.loadMockRequest(10000);

        //开始循环回放
        replay1.start(cache1);

    }
}
