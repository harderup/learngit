package 四种引用;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;

class PhantomReferenceDemo {
    public static void main(String[] args) {
        Object obj = new Object();
        ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>();
        PhantomReference phantomReference = new PhantomReference(obj,referenceQueue);

        System.out.println(phantomReference.get());//null虚引用的get()方法取值为null
        System.out.println(referenceQueue.poll());

        System.out.println("=============");
        obj = null;
        System.gc();
        System.out.println(phantomReference.get());
        System.out.println(referenceQueue.poll());

    }
}
