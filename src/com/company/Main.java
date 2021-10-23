package com.company;

import java.util.concurrent.*;
import java.util.concurrent.locks.*;


class Test {

    static String sayHello()  {
        return a;
    }

    static String b = sayHello(); // a static method is called to assign value to b.
    // but its a has not been initialized yet.

    static String a = "hello";

    static String c = sayHello(); // assignes "hello" to variable c
}



class Test2 {

    static String sayHello() {
        return a;
    }

    static final String b = sayHello(); // a static method is called to assign value to b.
    // but its a has not been initialized yet.

    static final String a = "hello";

    static final String c = sayHello(); // assignes "hello" to variable c
}


//class Test3 extends Test2 {
//    @Override
//    static String sayHello() {
//        return "";
//    }
//}

public class Main {

    final static Lock lock = new ReentrantLock();

//    synchronized
//    static void fff() {
//
//    }


    public static void main(String[] args) {
        System.out.println();

        String cc = Test.c;

        String bb = Test.b;
        String aa = Test.a;
        String bb2 = Test.b;

        String a = Test.sayHello();

        String bb3 = Test.b;

        int nn = 1;

//        Semaphore semaphore;
//        CyclicBarrier cyclicBarrier;
//        DelayQueue<> delayQueue; //!!!!!!!!!!
//        Phaser phaser;
//
//
//        ReadWriteLock lock = new ReentrantReadWriteLock();
//        Lock rLock1 = lock.readLock();
//        Lock wLock1 = lock.writeLock();


        //StampedLock lock2;
        //lock2.read

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

        //1
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

        //2
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

        //3
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
