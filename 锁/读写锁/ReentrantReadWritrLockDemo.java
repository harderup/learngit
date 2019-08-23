package com.读写锁;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReentrantReadWritrLockDemo {

    public static void main(String[] args) {
        final CaChe caChe = new CaChe();
        for (int i = 0;i<5;i++){
            final int temp = i;
            new Thread(new Runnable() {
                public void run() {
                    caChe.put(temp+"",temp+"");
                }
            },"AA").start();
        }
        for (int i = 0;i<5;i++){
            final int temp = i;
            new Thread(new Runnable() {
                public void run() {
                    caChe.get(temp+"");
                }
            },"BB").start();
        }
    }
}


class CaChe{
    //当做缓存
    private volatile Map<String,Object> map = new HashMap<String, Object>();

    //读写锁
    private ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
    /*
    写操作：原子+独占
    整个过程必须是一个完整的统一体，中间不许被分割，不许被打断
     */
    public void put(String key,Object value){
        try{
            reentrantReadWriteLock.writeLock().lock();
            System.out.println(Thread.currentThread().getName()+"\t正在写入"+key);
            Thread.sleep(300);
            map.put(key,value);
            System.out.println(Thread.currentThread().getName() + "\t写入完成");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            reentrantReadWriteLock.writeLock().unlock();
        }
    }

    /*
    读操作
     */
    public void get(String key){
        try {
            reentrantReadWriteLock.readLock().lock();
            System.out.println(Thread.currentThread().getName() + "\t正在读取：" + key);
            Thread.sleep(3000);
            Object result = map.get(key);
            System.out.println(Thread.currentThread().getName() + "\t读取完成: " + result);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            reentrantReadWriteLock.readLock().unlock();
        }
    }
}