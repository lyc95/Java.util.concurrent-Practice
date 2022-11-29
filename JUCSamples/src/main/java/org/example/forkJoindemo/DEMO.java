package org.example.forkJoindemo;


import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

class MyTask extends RecursiveTask<Integer> {

    //DIFF < 10
    private static final Integer VALUE = 10;
    private int start;
    private int end;
    private int result;

    public MyTask(int start, int end) {
        this.start = start;
        this.end = end;
    }

    //divide and conquer
    @Override
    protected Integer compute() {
        if (end - start <= VALUE) {
            for (int i = start; i <= end; i++) {
                result += i;
            }
        } else {
            int mid = (start + end) /2;
            MyTask subTask1 = new MyTask(start, mid);
            MyTask subTask2 = new MyTask(mid+1, end);
            //dividing the problem into sub tasks
            subTask1.fork();
            subTask2.fork();
            // join the tasks
            result = subTask1.join() + subTask2.join();
        }
        return result;
    }
}
public class DEMO {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        MyTask task = new MyTask(1, 100);
        //create poll and submit task
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTask<Integer> forkJoinTask = forkJoinPool.submit(task);
        //get result
        Integer res = forkJoinTask.get();
        System.out.println(res);
        forkJoinPool.shutdown();
    }
}
