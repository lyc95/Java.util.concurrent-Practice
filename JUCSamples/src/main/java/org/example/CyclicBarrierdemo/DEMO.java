package org.example.CyclicBarrierdemo;
import java.util.concurrent.CyclicBarrier;

public class DEMO {
    public static final int NUMBER = 7;
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(NUMBER, () -> {
            System.out.println("finally got all 7 dragon balls");
        });
        //define the process of gathering balls
        for (int i = 0; i < 7; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + " dragon ball collected");
                try {
                    //wait until nos of thread who invoked await() reached the barrier
                    //cyclicBarrier += 1
                    //it will call the runnable (2nd param) once it reaches the barrier
                    cyclicBarrier.await();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }, String.valueOf(i)).start();
        }

    }
}
