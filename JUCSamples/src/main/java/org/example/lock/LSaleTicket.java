package org.example.lock;

import java.util.concurrent.locks.ReentrantLock;

class LTicket {
    private int number = 200;
    private final ReentrantLock lock = new ReentrantLock();
    public void sale(){
        lock.lock();
        try {
            if (number > 0) {
                System.out.println(Thread.currentThread().getName() + ": sold " + (number--) + " remaining: " + number);
            }
        } finally {
            lock.unlock();
        }
    }
}
public class LSaleTicket {


    public static void main(String[] args) {
        LTicket ticket = new LTicket();
        new Thread(() -> {
            for (int i = 0; i < 40; i++) {
                ticket.sale();
            }
        }, "AA").start();
        new Thread(() -> {
            for (int i = 0; i < 40; i++) {
                ticket.sale();
            }
        }, "BB").start();

        new Thread(() -> {
            for (int i = 0; i < 40; i++) {
                ticket.sale();
            }
        }, "CC").start();
    }
}
