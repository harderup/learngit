package com.cas;

import org.omg.PortableInterceptor.Interceptor;

import java.util.concurrent.atomic.AtomicReference;

/*
 引用类型
 */
public class 引用类型CASDemo {
    public static void main(String[] args) {
        User user1 = new User("张三",12);
        User user2 = new User("李四",13);
        AtomicReference<User> atomicReference = new AtomicReference<User>();
        atomicReference.set(user1);
        System.out.println(atomicReference.compareAndSet(user1,user2)+"\t"+atomicReference.get().toString());
        System.out.println(atomicReference.compareAndSet(user1,user2)+"\t"+atomicReference.get().toString());
    }
}

class User{
    private String name;
    private Integer age;

    public User(String name, Integer age){
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "姓名："+name+",年龄："+age ;
    }
}
