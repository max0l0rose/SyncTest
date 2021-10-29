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

    static String b = sayHello(); // a static method is called to assign value to b.
    // but its a has not been initialized yet.

    final static String a = "hello";

    static String c = sayHello(); // assignes "hello" to variable c
}


class EarlyInitSingleton {
    final static EarlyInitSingleton INSTANCE = new EarlyInitSingleton();
    public static EarlyInitSingleton getInstance() {
        return INSTANCE;
    }

    // private constructor and other methods...
    int a = 1; //1
    static int b = 1; //2
}


class InitOnDemandSingleton {
    private static class InstanceHolder {
        private static final InitOnDemandSingleton INSTANCE = new InitOnDemandSingleton();
    }
    public static InitOnDemandSingleton getInstance() {
        return InstanceHolder.INSTANCE;
    }

    static int getC() {
        return d;
    }
    // private constructor and other methods...
    int a = 1;
    static int b = getC();
    static int c = 1;
    final static int d = 1;
}


enum EnumSingleton {
    INSTANCE; // 1

    int a = 1; //2
    static int b = 1; //3
    static int c = 1; //4
    static int d = 1; //5
    final static int e = 1; //0
}


//class Test3 extends Test2 {
//    @Override
//    static String sayHello() {
//        return "";
//    }
//}

public class Main {

    final static Lock lock = new ReentrantLock();

    static int a = 1;
    static Integer b = new Integer(1);
    static Integer c;

    static EarlyInitSingleton earlyInitSingleton;

    final static Object mutex = new Object();


    static void syncTest() {
        try {
            Thread.sleep(100);
            synchronized (mutex) {
                synchronized (mutex) {
                    mutex.notify();
                    int a = 1;
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) throws InterruptedException {
        System.out.println();

        //syncTest();

        Thread t1 = new Thread(() -> { syncTest(); });

        Thread t2 = new Thread(() -> {
            synchronized (mutex) {
                synchronized (mutex) {
                    System.out.println("111111");
                    //mutex.notify();
                    int a = 1;
                }
            }
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();


//        EarlyInitSingleton earlyInitSingleton1 = new EarlyInitSingleton();
//
//        EarlyInitSingleton earlyInitSingleton = EarlyInitSingleton.getInstance();
//
        //InitOnDemandSingleton initOnDemandSingleton = InitOnDemandSingleton.getInstance();

        //EnumSingleton enumSingleton = EnumSingleton.INSTANCE;

//        String cc = Test.c;
//
//        String bb = Test.b;
//        String aa = Test.a;
//        String bb2 = Test.b;
//
//        String aa2 = Test.sayHello();
//
//        String bb3 = Test.b;


        // !!!!!!!!!!!!!!!!!!! final happen before
        String cc2 = Test2.c;

        String bb22 = Test2.b;
        String aa22 = Test2.a;
        String bb222 = Test2.b;

        String aa222 = Test2.sayHello();

        String bb32 = Test2.b;

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
