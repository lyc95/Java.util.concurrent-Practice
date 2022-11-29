package org.example.countdownlatch;

import java.util.concurrent.CountDownLatch;

//演示6个同学离开后才可以锁门
public class demo {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 0; i < 6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + " left classroom");
                //count - 1
                countDownLatch.countDown();
            }, String.valueOf(i)).start();
        }
        //wait until count deducted to 0
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName() + " lock the classroom");
    }

}
