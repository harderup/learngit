package com.生产者消费者;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/*
    对线程之间按顺序调用，实现A>B>C三个线程启动，要求如下：
 * AA打印5次，BB打印10次，CC打印15次
 * 紧接着
 * AA打印5次，BB打印10次，CC打印15次
 * 。。。。
 * 来十轮
 */
public class Test {

    public static void main(String[] args) {
        TestResource test = new TestResource();
        new Thread(()->{
            for(int i = 0;i<10;i++){
                test.printA();
            }
        },"AA   ").start();

        new Thread(()->{
            for(int i = 0;i<10;i++){
                test.printB();
            }
        },"BB   ").start();

        new Thread(()->{
            for(int i = 0;i<10;i++){
                test.printC();
            }
        },"CC   ").start();
    }
}

class TestResource{
    private static int number = 0;
    private int num = 1; //1：A  2:B  3:C
    private ReentrantLock lock = new ReentrantLock();
    private Condition conditionA = lock.newCondition();
    private Condition conditionB = lock.newCondition();
    private Condition conditionC = lock.newCondition();

    public void printA(){
        try{
            lock.lock();
            while(num!=1){
                conditionA.await();
            }
            for(int i = 0;i<5;i++){
                System.out.println(Thread.currentThread().getName()+"输出\t"+(++number));
            }
            num = 2;
            conditionB.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void printB(){
        try{
            lock.lock();
            while(num!=2){
                conditionB.await();
            }
            for(int i = 0;i<10;i++){
                System.out.println(Thread.currentThread().getName()+"输出\t"+(++number));
            }
            num = 3;
            conditionC.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void printC(){
        try{
            lock.lock();
            while(num!=3){
                conditionC.await();
            }
            for(int i = 0;i<15;i++){
                System.out.println(Thread.currentThread().getName()+"输出\t"+(++number));
            }
            num = 1;
            conditionA.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}
