package com.example.j2se.collection;

import java.util.List;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Author wangxi
 * @Time 2020/1/8 20:42
 * 利用ReentrantReadWriteLock 实现线程安全的list
 */
public class ReadWriteList<E> {
    // 线程不安全的list
    private List<E> list;

    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private ReentrantReadWriteLock.ReadLock readLock = lock.readLock();
    private ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();

    public ReadWriteList(List<E> list) {
        this.list = list;
    }

    public E get(int index) {
        // index参数验证
        try {
            readLock.lock();
            return list.get(index);
        } finally {
            readLock.unlock();
        }
    }

    public void add(E element) {
        try {
            writeLock.lock();
            list.add(element);
        } finally {
            writeLock.unlock();
        }
    }
}
