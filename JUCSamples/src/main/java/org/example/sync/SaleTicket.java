package org.example.sync;


class Ticket {
    private int number = 30;

    public synchronized void sale(){
        if (number > 0) {
            System.out.println(Thread.currentThread().getName() + ": sold " + (number--) + " remaining: " + number);
        }
    }
}
public class SaleTicket {
    public static void main(String[] args) {
        final Ticket ticket = new Ticket();
        //Create 3 threads to execute the tasks
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 40; i++) {
                    ticket.sale();
                }
            }
        }, "AA").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 40; i++) {
                    ticket.sale();
                }
            }
        }, "BB").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 40; i++) {
                    ticket.sale();
                }
            }
        }, "CC").start();
    }
}
