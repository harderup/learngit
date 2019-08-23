package com.自旋锁;

import java.util.concurrent.atomic.AtomicReference;

public class SpinLockDemo {

    //原子引用线程
    AtomicReference<Thread> atomicReference = new AtomicReference();

    public void mylock() {
        Thread thread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName() + "\t come in");
        while (!atomicReference.compareAndSet(null, thread)) {

        }
    }

    public void myUnlock() {
        Thread thread = Thread.currentThread();
        atomicReference.compareAndSet(thread, null);
        System.out.println(Thread.currentThread().getName()+"\t invoked myunlock()");
    }
    public static void main(String[] args) {

        final SpinLockDemo spinLockDemo = new SpinLockDemo();

        new Thread(new Runnable() {
            public void run() {

                spinLockDemo.mylock();
                try {
                   Thread.sleep(300);
                }catch (Exception e){
                    e.printStackTrace();
                }
                spinLockDemo.myUnlock();
            }
        },"ThreadAA").start();

        try {
            Thread.sleep(3);
        }catch (Exception e){
            e.printStackTrace();
        }
        new Thread(new Runnable() {
            public void run() {
                spinLockDemo.mylock();
                spinLockDemo.myUnlock();
            }
        },"ThreadBB").start();
    }
}

