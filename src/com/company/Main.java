package com.company;

import java.util.concurrent.locks.*;

public class Main {

    final static Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        System.out.println();

        ReadWriteLock lock = new ReentrantReadWriteLock();
        Lock rLock1 = lock.readLock();
        Lock wLock1 = lock.writeLock();


        StampedLock lock2;
        lock2.read

        //SpinLock spinLock;

//        lock.lock();
//        lock.lock();
//
//        lock.unlock();
//        lock.unlock();
//
//        lock.lock();
//
//        lock.unlock();



//        final Object ref = new Object();
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                while(true) {
//                    synchronized(ref) {
//                        System.out.println("A");
//                    }
//                }
//            }
//        }).start();
//
//        new Thread(() -> {
//            while(true) {
//                synchronized(ref) {
//                    System.out.println("B");
//                }
//            }
//        }).start();
//
//        new Thread(() -> {
//            while(true) {
//                synchronized(ref) {
//                    System.out.println("C");
//                }
//            }
//        }).start();



        final Lock ref = new ReentrantLock(true);

        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true) {
                    ref.lock();
                    System.out.println("A");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    ref.unlock();
                }
            }
        }).start();

        new Thread(() -> {
            while(true) {
                ref.lock();
                System.out.println("-B");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                ref.unlock();
            }
        }).start();

        new Thread(() -> {
            while(true) {
                ref.lock();
                System.out.println("--C");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                ref.unlock();
            }
        }).start();


        System.out.println("OK");
    }
}
