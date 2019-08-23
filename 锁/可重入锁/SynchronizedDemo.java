package com.可重入锁;

public class SynchronizedDemo {
    public static void main(String[] args) {
        final MyThread myThread = new MyThread();

        new Thread(new Runnable() {
            public void run() {
                myThread.sendEmail();
            }
        },"Thread1").start();
        new Thread(new Runnable() {
            public void run() {
                myThread.sendSMS();
            }
        },"Thread2").start();

    }
}

class MyThread {

    public synchronized void sendEmail(){

        System.out.println(Thread.currentThread().getName()+"\t  invoke---sendEmail()");

        try{
            Thread.sleep(3000);
        }catch (Exception e){
            e.printStackTrace();
        }
        sendSMS();
    }

    public synchronized void sendSMS(){

        System.out.println(Thread.currentThread().getName()+"\t invoke----sendSMS()");
    }
}
