package com.example.businesssample.内存泄漏分析01.正确的用法;

import com.example.businesssample.内存泄漏分析01.HttpAsyncClient;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.concurrent.FutureCallback;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author wangxi
 * @Time 2020/5/6 18:11
 */
public class ReplayWithoutProblemCorrect {
    public List<String> loadMockRequest(int n){
        List<String> cache = new ArrayList<String>(n);
        for (int i = 0; i < n; i++) {
            cache.add("http://www.baidu.com?a="+i);
        }
        return cache;
    }

    public void start(List<String> cache) throws InterruptedException {

        HttpAsyncClient httpClient = new HttpAsyncClient();
        int i = 0;

        while (true){
            /**
            * 找到问题的原因，我们现在来优化代码，验证我们的结论。
             * 因为List<HttpUriRequest> cache1中会保存回调对象，
            * 所以我们不能缓存请求类，只能缓存基本数据，在使用时进行动态的生成，来保证回调对象的及时回收。
            */
            String url = cache.get(i%cache.size());
            final HttpGet request = new HttpGet(url);
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
            Thread.sleep(100);
        }
    }
}
