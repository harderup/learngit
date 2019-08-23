package com.cas;

import java.util.concurrent.atomic.AtomicReference;
/*
    多线程并发情况下会发生ABA问题


 */
public class ABADemo {
    static AtomicReference<Integer> atomicReference = new AtomicReference<Integer>(100);

    public static void main(String[] args) {
        //线程一
        new Thread(new Runnable() {
            public void run() {
                atomicReference.compareAndSet(100,101);
                atomicReference.compareAndSet(101,100);
            }
        },"Thread1").start();

        //线程二
        new Thread(new Runnable() {
            public void run() {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(atomicReference.compareAndSet(100,2019)+"\t"+atomicReference.get()
                );
            }
        },"Thread2").start();
    }


}
