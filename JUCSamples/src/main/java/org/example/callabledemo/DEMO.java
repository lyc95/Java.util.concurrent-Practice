package org.example.callabledemo;


import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

class MyThread1 implements Runnable{

    @Override
    public void run() {

    }
}

class MyThread2 implements Callable{

    @Override
    public Integer call() throws Exception {
        return 200;
    }
}
public class DEMO {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        new Thread(new MyThread1(), "A").start();
        //Callable error not support
        //new Thread(new MyThread2(), "B").start();

        // Runnable Interface -> FutureTask class
        // Constructor > FutureTask(Callable c)

        FutureTask<Integer> futureTask1 = new FutureTask<>(new MyThread2());
        //lambda
        FutureTask<Integer> futureTask2 = new FutureTask<>(() -> {
            System.out.println(Thread.currentThread().getName());

            return 1024;
        });
        new Thread(futureTask1, "B").start();
        new Thread(futureTask2, "C").start();

//        while (!futureTask1.isDone()) {
//            System.out.println("wait...");
//        }
        System.out.println(futureTask1.get());
        System.out.println(futureTask2.get());
        System.out.println(futureTask2.get());
        System.out.println("Main over");

    }

}
