package com.volatle;

public class VolatileDemo {

    /*
        volatile可以保证可见性，及时通知其他线程，主物理内存的值已经被修改
        1、验证volatile的可见性
           令number = 0，number变量在没有volatile修饰之前没有可见性，main线程会一直死循环
     */
    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        myThread.start();

        while(myThread.number==0){
            //当main线程看到number被修改为50时，退出循环
        }
        System.out.println(Thread.currentThread().getName() + "\t update value:" + myThread.number);


    }
}

class MyThread extends Thread{
    volatile int number = 0;

    public void addNumber(){
        number = 50;
    }

    public void run(){
        System.out.println(Thread.currentThread().getName() + "\t come in");
        try {
            //线程暂停3s
            Thread.sleep(3);
            addNumber();
            System.out.println(Thread.currentThread().getName() + "\t update value:" + number);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}