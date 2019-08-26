package OutOfMemoryError;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/*
    堆溢出
 */
public class HeapSpaceDemo {

    public static void test1(){
        Byte [] bytes = new Byte[20*1024*1024];
    }

    public static void test2(){
        List<Random> list = new ArrayList<>();
        while (true){
            list.add(new Random());
        }
    }
    public static void main(String[] args) {
        test2();
    }
}
