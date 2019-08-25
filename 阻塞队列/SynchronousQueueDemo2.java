package com.阻塞队列;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

public class SynchronousQueueDemo2 {

    public static void main(String[] args) {
        BlockingQueue<String> blockingQueue = new SynchronousQueue<>();

        new Thread(()->{
           try{
               System.out.println( blockingQueue.add("a"));
           }catch (Exception e){
               e.printStackTrace();
           }
        },"AAA").start();

        new Thread(()->{
            try{
                TimeUnit.SECONDS.sleep(3);
                System.out.println( blockingQueue.remove());
            }catch (Exception e){
                e.printStackTrace();
            }
        },"BBB").start();
    }
}
