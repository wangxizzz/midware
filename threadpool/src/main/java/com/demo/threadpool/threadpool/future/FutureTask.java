package com.demo.threadpool.threadpool.future;

/**
 * @Author wangxi
 * @Time 2019/11/24 17:34
 */
public class FutureTask<T> implements Runnable, Future<T>{

    private Callable<T> callable;
    private T result;
    private Object notify;

    public FutureTask(Callable callable) {
        this.callable = callable;
        notify = new Object();
    }

    @Override
    public T get() throws InterruptedException{
        synchronized (notify) {
            while (result == null) {
                notify.wait();
            }
        }
        return result;
    }

    @Override
    public void run() {
        result = callable.call();
        synchronized (notify) {
            notify.notify();
        }
    }
}
