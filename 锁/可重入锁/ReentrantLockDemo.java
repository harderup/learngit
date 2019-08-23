package com.可重入锁;

import java.util.concurrent.locks.ReentrantLock;
/*
ReentrantLock是可重入的锁
 */
public class ReentrantLockDemo {

    public static void main(String[] args) {
        final Test test = new Test();
        new Thread(new Runnable() {
            public void run() {
                test.get();
            }
        },"Thread1").start();
        new Thread(new Runnable() {
            public void run() {
                test.get();
            }
        },"Thread2").start();
    }
}

class Test{
    ReentrantLock lock = new ReentrantLock();
    public void get(){
        try{
            lock.lock();
            lock.lock();
            System.out.println(Thread.currentThread().getName()+"\t get()");
        }finally {
            lock.unlock();
            lock.unlock();
        }
    }

    public void set(){
        try{
            lock.lock();
            System.out.println(Thread.currentThread().getName()+"\t set()");
            get();
        }finally {
            lock.unlock();
        }
    }
}