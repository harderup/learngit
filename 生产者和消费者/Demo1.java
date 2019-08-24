package com.生产者消费者;

//传统的生产者消费者模式：用ReentrantLock实现

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/*
    1、线程   操作  资源类
    2、判断   干活  通知
    3、防止虚假唤起机制
 */
public class Demo1 {

    public static void main(String[] args) {
        Resources resources = new Resources();

        new Thread(()->{
            for(int i = 0;i<6;i++){
                resources.producer();
            }
        }).start();

        new Thread(()->{
            for(int i = 0;i<6;i++){
                resources.customer();
            }
        }).start();

    }
}

class Resources{
    private int number = 0;
    private ReentrantLock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    //生产
    public void producer(){
       try{
           lock.lock();
           while(number!=0){
               condition.await();
           }
           number++;
           System.out.println(Thread.currentThread().getName()+"生产了"+number);
           //唤醒
           condition.signalAll();
       }catch (Exception e){
           e.printStackTrace();
       }finally {
           lock.unlock();
       }
    }

    //消费者
    public void customer(){
        try{
            lock.lock();
            while(number==0){
                condition.await();
            }
            number--;
            System.out.println(Thread.currentThread().getName()+"消费了"+number);
            //唤醒
            condition.signalAll();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}
