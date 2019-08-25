package com.阻塞队列;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class ArrayBlockingQueueDemo {


    //超出队列界限会抛出异常
    public static void test1(){
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);
        System.out.println(blockingQueue.add("a"));
        System.out.println(blockingQueue.add("b"));
        System.out.println(blockingQueue.add("b"));
        //blockingQueue.add("d");//队列大小为3，超出大小会抛出异常

        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        //System.out.println(blockingQueue.remove());//超出，抛异常
        blockingQueue.offer("q");
        System.out.println(blockingQueue.poll());

    }

    //返回boolean类型
    public static void test2(){
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);
        System.out.println(blockingQueue.offer("a"));
        System.out.println(blockingQueue.offer("d"));
        System.out.println(blockingQueue.offer("c"));
        System.out.println(blockingQueue.offer("d"));//false

        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());//null
    }

    //会阻塞
    public static void test3() throws InterruptedException {
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);
        blockingQueue.put("a");
        blockingQueue.put("b");
        blockingQueue.put("c");
        blockingQueue.put("d");

        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
    }

    //设置时间
    public static void test4() throws InterruptedException {
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);
        System.out.println( blockingQueue.offer("a",3, TimeUnit.SECONDS));
        System.out.println( blockingQueue.offer("b",3, TimeUnit.SECONDS));
        System.out.println( blockingQueue.offer("c",3, TimeUnit.SECONDS));
        System.out.println( blockingQueue.offer("d",10, TimeUnit.SECONDS));
        blockingQueue.poll(1,TimeUnit.SECONDS);
        blockingQueue.poll(1,TimeUnit.SECONDS);
        blockingQueue.poll(1,TimeUnit.SECONDS);

    }
    public static void main(String[] args) throws InterruptedException {
        test4();
    }
}
