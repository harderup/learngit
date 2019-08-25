package com.阻塞队列;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

public class SynchronousQueueDemo {

    public static void main(String[] args) {
        BlockingQueue<String> blockingQueue = new SynchronousQueue<>();

        new Thread(()->{
           try{
               System.out.println(Thread.currentThread().getName()+"生产第1个！");
               blockingQueue.put("a");
               System.out.println(Thread.currentThread().getName()+"生产第2个！");
               blockingQueue.put("b");
               System.out.println(Thread.currentThread().getName()+"生产第3个！");
               blockingQueue.put("c");
           }catch (Exception e){
               e.printStackTrace();
           }
        },"AAA").start();

        new Thread(()->{
            try{
                TimeUnit.SECONDS.sleep(3);
                System.out.println(Thread.currentThread().getName()+"消费第1个！");
                blockingQueue.take();
                TimeUnit.SECONDS.sleep(3);
                System.out.println(Thread.currentThread().getName()+"消费第2个！");
                blockingQueue.take();
                TimeUnit.SECONDS.sleep(3);
                System.out.println(Thread.currentThread().getName()+"消费第3个！");
                blockingQueue.take();
            }catch (Exception e){
                e.printStackTrace();
            }
        },"BBB").start();
    }
}
