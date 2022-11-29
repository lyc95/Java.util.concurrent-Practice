package org.example.completable;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class demo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //async no return val
        CompletableFuture<Void> completableFuture1 =CompletableFuture.runAsync(() -> {
            System.out.println(Thread.currentThread().getName() + " completableFuture1");
        });
        completableFuture1.get();
        //async with return val
        CompletableFuture<Integer> completableFuture2 = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + " completableFuture2");
            return 1024;
        });
        completableFuture2.whenComplete((t,u) -> {
            System.out.println("t: " + t); // return val if ok null if no return val
            System.out.println("u: " + u); // contains exception details if any
        }).get();
    }
}
