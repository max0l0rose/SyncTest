package com.company;

import java.util.NavigableMap;
import java.util.Queue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.*;


public class Main {

    final static Lock reentrantLock = new ReentrantLock();
    final static Condition condition = reentrantLock.newCondition();
    final static Condition condition2 = reentrantLock.newCondition();

    //final static Object mutex = new Object();
    static void thFunc1() {

        reentrantLock.lock();
        try {
            //synchronized (mutex) {
                //synchronized (mutex) {
                    //mutex.notify();
            System.out.println("th1 1");
            Thread.sleep(2000);
            System.out.println("th1 2");
            condition.signal();
            Thread.sleep(2000);
            System.out.println("th1 3");
            condition2.signal();
                //}
            //}
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        finally {
            reentrantLock.unlock();
        }
    }


    public static void main(String[] args) throws InterruptedException {
//        System.out.println();

        Thread t1 = new Thread(() -> { thFunc1(); });

        Thread t2 = new Thread(() -> {
            //synchronized (mutex) {
            //    synchronized (mutex) {

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            reentrantLock.lock();
            try {
                System.out.println("th2 1");
                condition.await();
                System.out.println("th2 2");
                condition2.await();
                System.out.println("th2 3");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            finally {
                reentrantLock.unlock();
            }
                    //mutex.notify();
            //        int a = 1;
            //    }
            //}
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();


        Semaphore semaphore;
//        CyclicBarrier cyclicBarrier;
//        DelayQueue<> delayQueue; //!!!!!!!!!!
//        Phaser phaser;
        //StampedLock lock2;
        //lock2.read

        //SpinLock spinLock;


//        ReadWriteLock lock = new ReentrantReadWriteLock();
//        Lock rLock1 = lock.readLock();
//        Lock wLock1 = lock.writeLock();

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


//
//        final Lock ref = new ReentrantLock(true);
//
//        //1
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                while(true) {
//                    ref.lock();
//                    System.out.println("A");
//                    try {
//                        Thread.sleep(1000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    ref.unlock();
//                }
//            }
//        }).start();
//
//        //2
//        new Thread(() -> {
//            while(true) {
//                ref.lock();
//                System.out.println("-B");
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                ref.unlock();
//            }
//        }).start();
//
//        //3
//        new Thread(() -> {
//            while(true) {
//                ref.lock();
//                System.out.println("--C");
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                ref.unlock();
//            }
//        }).start();
//
//
//        System.out.println("OK");
    }
}
