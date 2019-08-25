package com.死锁;

import java.util.concurrent.TimeUnit;
/*
  可以用jps -l 定位进程号和jstack 线程号  来查看具体情况
 */
public class Demo {

    public static void main(String[] args) {
        String lockA = "lockA";
        String lockB = "lockB";

        new Thread(new DeadLock(lockA,lockB)).start();
        new Thread(new DeadLock(lockB,lockA)).start();
    }
}

class DeadLock implements Runnable{

    private String lockA;
    private String lockB;

    public DeadLock(String lockA,String lockB){
        this.lockA = lockA;
        this.lockB = lockB;
    }

    public void run() {
        synchronized (lockA){
            System.out.println(Thread.currentThread().getName() + "\t自己持有：" + lockA + "\t尝试获得：" + lockB);
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lockB){
                System.out.println(Thread.currentThread().getName() + "\t自己持有：" + lockB + "\t尝试获得：" + lockA);
            }
        }
    }
}