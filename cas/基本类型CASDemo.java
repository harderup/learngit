package com.cas;

import java.util.concurrent.atomic.AtomicInteger;
/*

CAS：compareandswap
有三个参数：内存地址，期望的值，更新的值 如果内存地址的值和期望的值相等，则内存地址的值替换成要更新的值

CAS存在的问题：
1、循环时间长，CPU消耗大
2、只能保证一个共享变量的原子操作
3、ABA问题
 */
public class 基本类型CASDemo {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(100);
        System.out.println(atomicInteger.compareAndSet(100,101)+"\t"+atomicInteger.get());
        System.out.println(atomicInteger.compareAndSet(100,102)+"\t"+atomicInteger.get());
    }
}
