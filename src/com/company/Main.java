package com.company;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {

    public static void main(String[] args) {
        System.out.println();

        Lock lock = new ReentrantLock();

        lock.lock();
        lock.lock();

        lock.unlock();
        lock.unlock();

        System.out.println("OK");
    }
}
