package com.并发工具类;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo {

    static CountDownLatch countDownLatch = new CountDownLatch(6);
    public static void main(String[] args) {

        for(int i =1;i<=6;i++){
            new Thread(new Runnable() {
                public void run() {
                    System.out.println(Thread.currentThread().getName()+"被灭！");
                    countDownLatch.countDown();

                }
            },CountEnum.forEach_CuntryEnum(i).getName()).start();

        }
        try{
            countDownLatch.await();
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("秦国统一天下！");
    }
}
