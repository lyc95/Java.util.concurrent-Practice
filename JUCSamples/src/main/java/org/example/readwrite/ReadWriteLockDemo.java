package org.example.readwrite;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class MyCache {
    private volatile Map<String, Object> map = new HashMap<>();
    private ReadWriteLock rwLock = new ReentrantReadWriteLock();
    //put
    public void put(String key, Object value) {
        rwLock.writeLock().lock();
        System.out.println(Thread.currentThread().getName()+ " is writing " + key);
        try {
            TimeUnit.MICROSECONDS.sleep(300);
            map.put(key, value);
            System.out.println(Thread.currentThread().getName()+ " finished writing " + key);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            //release write lock
            rwLock.writeLock().unlock();
        }
    }
    //get
    public Object get(String key) {
        rwLock.readLock().lock();
        Object res = null;
        System.out.println(Thread.currentThread().getName()+ " is reading " + key);
        try {
            TimeUnit.MICROSECONDS.sleep(300);
            res = map.get(key);
            System.out.println(Thread.currentThread().getName()+ " finished reading " + key);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            rwLock.readLock().unlock();
        }
        return res;
    }

}
public class ReadWriteLockDemo {
    public static void main(String[] args) {
        MyCache myCache = new MyCache();
        for (int i = 0; i < 5; i++) {
            final int num = i;
            new Thread(() -> {
                myCache.put(num + "", num + "");
            }, String.valueOf(i+1)).start();
        }
        for (int i = 0; i < 5; i++) {
            final int num = i;
            new Thread(() -> {
                myCache.get(num + "");
            }, String.valueOf(i+1)).start();
        }
    }
}
