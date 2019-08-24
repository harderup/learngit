package com.并发工具类;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {


    public static void main(String[] args) {
        final CyclicBarrier cyclicBarrier = new CyclicBarrier(7,new Thread(new Runnable() {
            public void run() {
                System.out.println("集齐神龙！");
            }
        }));

        for(int i = 1;i<8;i++){
            int temp = i;
            new Thread(new Runnable() {
                public void run() {
                    System.out.println("收集神龙！");
                    try {
                        cyclicBarrier.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                }
            },"第"+i).start();
        }
    }
}
