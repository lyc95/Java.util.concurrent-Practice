package org.example.sync;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SyncLockDemo {

    public synchronized void add(){
        add();
    }
    public static void main(String[] args) {
//        new SyncLockDemo().add();
//        Object o = new Object();
//        new Thread(() -> {
//            synchronized (o) {
//                System.out.println(Thread.currentThread().getName() + " out-most layer");
//                synchronized (o) {
//                    System.out.println(Thread.currentThread().getName() + " middle layer");
//                    synchronized (o) {
//                        System.out.println(Thread.currentThread().getName() + " inner-most layer");
//                    }
//                }
//            }
//        }, "AA").start();
        LockDemo();
    }

    public static void LockDemo(){
        //Lock 演示可重入锁
        Lock lock = new ReentrantLock();
        new Thread(() -> {
            try {
                lock.lock();
                System.out.println(Thread.currentThread().getName() + " tut-most layer");
                try {
                    lock.lock();
                    System.out.println(Thread.currentThread().getName() + " middle layer");
                    try {
                        lock.lock();
                        System.out.println(Thread.currentThread().getName() + " inner-most layer");
                    } finally {
                        lock.unlock();
                    }
                } finally {
                    lock.unlock();
                }
            } finally {
                //lock.unlock();
            }
        }, "t1").start();
        new Thread(() -> {
            lock.lock();
            //app will be blocked here as the lock not been release yet as shown above
            System.out.println(Thread.currentThread().getName());
            lock.unlock();
        }, "t2").start();
    }
}
