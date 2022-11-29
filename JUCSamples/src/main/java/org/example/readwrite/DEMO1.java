package org.example.readwrite;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class DEMO1 {
    public static void main(String[] args) {
        ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
        ReentrantReadWriteLock.ReadLock readLock = rwLock.readLock();
        ReentrantReadWriteLock.WriteLock writeLock = rwLock.writeLock();

        // case 1
        // get write lock
        writeLock.lock();
        System.out.println("write-----");
        //get readlock
        readLock.lock();
        System.out.println("read----");
        //output: write---- read---
        //we can read in write
        writeLock.unlock();
        readLock.unlock();

        // case 2
        //get read lock
        readLock.lock();
        System.out.println("read----");

        //get write lock
        writeLock.lock();
        System.out.println("write----");
        // output:read----
        // we can not write during read
    }
}
