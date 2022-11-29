package org.example.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolDemo1 {
    public static void main(String[] args) {
        //1. Pool with 5 threads
        //ExecutorService threadPool = Executors.newFixedThreadPool(5);
        //2. Pool with single thread
        //ExecutorService threadPool = Executors.newSingleThreadExecutor();
        //3. Elastic thread
        // It will determine how many threads needed
        ExecutorService threadPool = Executors.newCachedThreadPool();
        try{
            for (int i = 0; i < 100; i++) {
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + " is processing");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }
    }
}
