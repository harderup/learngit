package 四种引用;

import java.util.HashMap;
import java.util.WeakHashMap;

public class WeakHashMapDemo {
    public static void main(String[] args) {
        System.out.println("HashMap的引用：");
        myHashMap();
        System.out.println("===============================");
        System.out.println("WeakHashMap的引用");
        myWeakHashMap();

    }

    public static void myHashMap(){
        HashMap<Integer,String> hashMap = new HashMap<>();
        Integer integer = new Integer(1);
        String str = "myhashmap";

        hashMap.put(integer,str);
        System.out.println(hashMap);

        integer = null;
        System.out.println(hashMap);

        System.gc();
        System.out.println(hashMap);
    }

    public static void myWeakHashMap(){
        WeakHashMap<Integer,String> weakHashMap = new WeakHashMap<>();
        Integer integer = new Integer(2);
        String str = "myhashmap";

        weakHashMap.put(integer,str);
        System.out.println(weakHashMap);

        integer = null;
        System.out.println(weakHashMap);

        System.gc();
        System.out.println(weakHashMap);
    }
}
