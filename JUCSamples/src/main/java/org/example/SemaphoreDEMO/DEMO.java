package org.example.SemaphoreDEMO;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

//6 cars park 3 slots
public class DEMO {
    public static void main(String[] args) {
        // 3 permits/slots
        Semaphore semaphore = new Semaphore(3);
        // 6 thread/cars
        for (int i = 0; i < 6; i++) {
            new Thread(() -> {
                try {
                    // get slot/permit, block until one is available
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + " get the slot/permit");
                    TimeUnit.SECONDS.sleep(new Random().nextInt(5));
                    System.out.println(Thread.currentThread().getName() + " release the slot/permit");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } finally {
                    semaphore.release();
                }
            }, String.valueOf(i+1)).start();
        }
    }
}
