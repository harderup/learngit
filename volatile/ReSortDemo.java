package com.volatle;

public class ReSortDemo {
    int a = 0;
    boolean flag = false;

    //多线程环境中，线程的交替执行，由于编译器优化会重排序，
    //两个线程中使用的变量能否保证一致性是无法确定的，结果无法预测
    public void method01(){
        a = 1;
        flag = true;
    }
    public void method02(){
        if(flag){
            a = a + 5;
            System.out.println("\"a\" value is "+a);
        }
    }
}
