package com.example.businesssample.内存泄漏分析01;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;

import java.io.IOException;

/**
 * @Author wangxi
 * @Time 2020/5/6 17:10
 */
public class HttpAsyncClient {
    private CloseableHttpAsyncClient httpclient;

    public HttpAsyncClient() {
        httpclient = HttpAsyncClients.createDefault();
        httpclient.start();
    }

    public void execute(HttpUriRequest request, FutureCallback<HttpResponse> callback){
        httpclient.execute(request, callback);
    }

    public void close() throws IOException {
        httpclient.close();
    }
}
