package com.生产者消费者;

import javax.jws.Oneway;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class 生产者消费者阻塞队列版 {

    public static void main(String[] args) {

        ProResources proResources = new ProResources(new ArrayBlockingQueue<>(10));
        new Thread(()->{
            try {
                proResources.producer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"生产者  ").start();

        new Thread(()->{
            try {
                proResources.consumer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"生产者  ").start();

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        proResources.stop();

    }
}

class ProResources{

    private volatile boolean flag = true;//默认开启，进行生产和消费
    private AtomicInteger atomicInteger = new AtomicInteger();
    private BlockingQueue blockingQueue = null;

    public ProResources(BlockingQueue blockingQueue){
        this.blockingQueue = blockingQueue;
    }

    //生产者
    public void producer() throws InterruptedException {
        String result = null;
        boolean reValue = false;
        while(flag){
            //进行原子自增操作
            result = atomicInteger.incrementAndGet()+"";
            reValue = blockingQueue.offer(result,2, TimeUnit.SECONDS);
            if(reValue){
                System.out.println(Thread.currentThread().getName() + "\t插入队列" + result + "成功");
            } else {
                System.out.println(Thread.currentThread().getName() + "\t插入队列" + result + "失败");
            }
            TimeUnit.SECONDS.sleep(1);
        }

        System.out.println("停止生产：flag = false");
    }

    public void consumer() throws InterruptedException {
        Object result = null;
        while(flag){
            result = blockingQueue.poll(2, TimeUnit.SECONDS);
            if(null == result || result.equals("")){
                flag = false;
                System.out.println(Thread.currentThread().getName() + "\t超过2s没有可消费产品");
                return;
            }
            System.out.println(Thread.currentThread().getName()+"消费队列消费\t"+result+"产品");
        }
    }

    //终止
    public void stop(){
        flag = false;
    }
}
