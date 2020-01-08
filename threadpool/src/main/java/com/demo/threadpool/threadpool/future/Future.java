package com.demo.threadpool.threadpool.future;

/**
 * @Author wangxi
 * @Time 2019/11/24 17:32
 */
public interface Future<T> {
    /**
     * 获取结果
     *
     * @return 结果
     * @throws InterruptedException
     */
    T get() throws InterruptedException;
}
