package com.demo.threadpool.threadpool;

import com.demo.threadpool.threadpool.future.Callable;
import com.demo.threadpool.threadpool.future.Future;
import com.demo.threadpool.threadpool.future.FutureTask;
import com.demo.threadpool.threadpool.notify.Notify;
import com.demo.threadpool.threadpool.util.ConcurrentHashSet;
import lombok.extern.slf4j.Slf4j;

import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author wangxi
 * @Time 2019/11/24 17:25
 * 实现简易线程池
 */
@Slf4j
public class MyThreadPoolExecutor {
    private final ReentrantLock lock = new ReentrantLock();
    private volatile int corePoolSize;
    private volatile int maxPoolSize;
    private long keepAliveTime;
    private TimeUnit timeUnit;
    private BlockingQueue<Runnable> workQueue;
    private volatile Set<Worker> workers;
    // 是否关闭线程池
    private AtomicBoolean isShutDown = new AtomicBoolean(false);
    // 提交到线程池中任务总数
    private AtomicInteger totalTask = new AtomicInteger(0);
    // 线程池任务全部执行完毕后的通知组件
    private Object shutDownNotify;
    private Notify notify;

    public MyThreadPoolExecutor(int corePoolSize, int maxPoolSize,
                                long keepAliveTime, TimeUnit timeUnit,
                                BlockingQueue<Runnable> workQueue, Notify notify) {
        this.corePoolSize = corePoolSize;
        this.maxPoolSize = maxPoolSize;
        this.keepAliveTime = keepAliveTime;
        this.timeUnit = timeUnit;
        this.workQueue = workQueue;
        this.notify = notify;
        this.workers = new ConcurrentHashSet<>();
    }

    // 有返回值提交任务
    public <T> Future<T> submit(Callable<T> callable) {
        FutureTask<T> futureTask = new FutureTask<>(callable);
        execute(futureTask);
        return futureTask;
    }

    /**
     * 执行任务
     *
     * @param runnable 需要执行的任务
     */
    public void execute(Runnable runnable) {
        if (runnable == null) {
            throw new NullPointerException("提交的任务为空");
        }
        if (isShutDown.get()) {
            log.info("线程池已关闭,不能再提交任务");
            return;
        }
        totalTask.incrementAndGet();
        if (workers.size() < corePoolSize) {
            addWorker(runnable, true);
            return;
        }
        if (workQueue.offer(runnable)) {
            if (isShutDown.get()) {
                log.info("线程池已关闭,不能再提交任务");
                return;
            }
            if (workers.size() == 0) {
                addWorker(runnable, true);
            }
        }
        // 说明队列已满
        else {
            if (workers.size() < maxPoolSize) {
                addWorker(runnable, true);
                return;
            } else {
                log.info("线程池已满，丢弃任务");
            }
        }
    }

    public void addWorker(Runnable runnable, boolean isNewTask) {
        Worker worker = new Worker(runnable, isNewTask);
        worker.startTask();
        workers.add(worker);
    }

    /**
     * 工作线程
     */
    public class Worker extends Thread{
        // 干活的线程
        private Thread thread ;
        // 任务
        private Runnable task;
        // true-->创建worker线程执行，false-->从worker队列拿
        private boolean isNewTask;
        public Worker(Runnable task, boolean isNewTask) {
            this.task = task;
            this.isNewTask = isNewTask;
            thread = this;
        }

        public void startTask() {
            this.thread.start();
        }

        public void close() {
            thread.interrupt();
        }

        @Override
        public void run() {

        }
    }
}
