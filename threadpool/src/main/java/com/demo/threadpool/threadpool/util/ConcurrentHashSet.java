package com.demo.threadpool.threadpool.util;

import java.util.AbstractSet;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author wangxi
 * @Time 2019/11/24 18:14
 * 定义并发安全的Set
 * 也可直接使用java.util.Collections#newSetFromMap
 */
public class ConcurrentHashSet<T> extends AbstractSet<T> {
    private final ConcurrentHashMap<T, Object> map;
    private final Object PRESENT = new Object();
    private AtomicInteger size = new AtomicInteger();

    public ConcurrentHashSet() {
        map = new ConcurrentHashMap<>();
    }
    public ConcurrentHashSet(int initCapacity) {
        map = new ConcurrentHashMap<>(initCapacity);
    }

    @Override
    public Iterator<T> iterator() {
        return map.keySet().iterator();
    }

    @Override
    public boolean add(T t) {
        size.incrementAndGet();
        // 第一次add t时，返回null，进而返回true，
        // 第二次再add 相同t时，会返回PRESENT,进而返回false,值仍然put进去了
        return map.put(t, PRESENT) == null;
    }

    @Override
    public boolean remove(Object o) {
        size.decrementAndGet();
        return map.remove(o) == PRESENT;
    }

    @Override
    public void clear() {
        size = new AtomicInteger(0);
        map.clear();
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public int size() {
        return size.get();
    }

    @Override
    public boolean contains(Object o) {
        return map.containsKey(o);
    }
}
