package com.example.businesssample.内存泄漏分析01;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.concurrent.FutureCallback;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author wangxi
 * @Time 2020/5/6 17:12
 *
 * 这里以回放百度为例，创建10000条mock数据放入缓存列表。
 * 回放时，以 while 循环每100ms 发送一个请求出去。具体代码如下:
 */
public class ReplayWithProblem {


    public List<HttpUriRequest> loadMockRequest(int n) {
        List<HttpUriRequest> cache = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            HttpGet request = new HttpGet("http://www.baidu.com?a=" + i);
            cache.add(request);
        }
        return cache;

    }

    public void start(List<HttpUriRequest> cache) throws InterruptedException {
        HttpAsyncClient httpClient = new HttpAsyncClient();
        int i = 0;

        while (true) {
            // 循环队列的写法。利用取模
            final HttpUriRequest request = cache.get(i % cache.size());
            /**
             * 最终分析出内存泄漏的原因：FutureCallback这个是ReplayWithProblem$1代表内部类，
             * FutureCallback没有在http调用完毕之后及时回收，因为它被HttpUriRequest强引用、
             * List<HttpUriRequest> cache 又强引用HttpAsyncClient，因此造成内存泄漏
             */
            httpClient.execute(request, new FutureCallback<HttpResponse>() {
                public void completed(final HttpResponse response) {
                    System.out.println(request.getRequestLine() + "->" + response.getStatusLine());
                }

                public void failed(final Exception ex) {
                    System.out.println(request.getRequestLine() + "->" + ex);
                }

                public void cancelled() {
                    System.out.println(request.getRequestLine() + " cancelled");
                }

            });
            i++;
            Thread.sleep(1000);
        }
    }
}
