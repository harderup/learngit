package OutOfMemoryError;

import java.util.ArrayList;
import java.util.List;

/*
    -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:MaxDirectMemorySize=5m

    产生异常：java.lang.OutOfMemoryError: GC overhead limit exceeded
 */

//超过98%的时间用来GC并且回收了不到2%的内存
public class GCOverHeadDemo {
    public static void main(String[] args) {
        int i = 0;
        List<String> list = new ArrayList<>();
        try{
            while(true){
                list.add(String.valueOf(++i).intern());
            }
        }catch (Throwable e){
            System.out.println("****************8"+i);
            e.printStackTrace();
            throw e;
        }
    }
}
