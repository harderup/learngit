package com.volatle;



import java.util.concurrent.atomic.AtomicInteger;


public class VolatileDemo2 {

    /*
        验证volatile不保证原子性

        为什么不保证原子性：

        解决方法
        1、用synchronized
        2、用原子类AtomicInteger
     */
    public static void main(String[] args) {

        final MyThread2 myThread = new MyThread2();
        for(int i = 0;i<20;i++){
            new Thread(new Runnable() {

                public void run() {
                    for(int i = 0;i<1000;i++){
                        myThread.addNumber();
                        myThread.addNumberAtomic();
                    }
                }
            }).start();

        }

        while(Thread.activeCount()>2){
            Thread.yield();
        }
        System.out.println(Thread.currentThread().getName()+"\t finally num value is "+myThread.number);
        System.out.println(Thread.currentThread().getName()+"\t finally atomicnum value is "+myThread.atomicInteger);

    }
}

class MyThread2 extends Thread{
    volatile  int number = 0;

    public void addNumber(){
        number++;
    }


    AtomicInteger atomicInteger = new AtomicInteger();

    public void addNumberAtomic(){
        //默认自增1
        atomicInteger.getAndIncrement();

    }

}