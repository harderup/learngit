package com.并发工具类;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

//多个线程抢多个资源
/*
模拟3辆车抢6个车位
 */
public class SemaphoreDemo {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);//3个空车位
        for(int i =1;i<=6;i++){
            //6个车
            int finalI = i;
            new Thread(()->{
                try {
                    semaphore.acquire();
                    System.out.println("Car"+ finalI +"    抢到车位！");
                    Thread.sleep(3);
                    System.out.println("Car"+ finalI +"     3秒后离开车位！");
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    semaphore.release();
                }
            }).start();
        }
    }
}
