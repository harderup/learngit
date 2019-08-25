package 四种引用;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

public class WeakReferenceDemo {
    public static void main(String[] args) {
        Object obj = new Object();
        //引用队列
        ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>();
        WeakReference<Object> weakReference = new WeakReference<Object>(obj,referenceQueue);

        System.out.println(obj);
        System.out.println(weakReference.get());
        System.out.println(referenceQueue.poll());
        System.out.println("=====================");
        obj = null;
        System.gc();
        System.out.println(obj);
        System.out.println(weakReference.get());
        System.out.println(referenceQueue.poll());

    }
}
