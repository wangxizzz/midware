package com.demo.threadpool.threadpool.future;

/**
 * @Author wangxi
 * @Time 2019/11/24 17:33
 */
public interface Callable<T> {
    /**
     * 执行任务
     * @return 执行结果
     */
    T call();
}
