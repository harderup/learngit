package OutOfMemoryError;


import java.nio.ByteBuffer;

/*
    本地内存溢出
     -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:MaxDirectMemorySize=5m 本地内存最大为5M
    抛出异常：java.lang.OutOfMemoryError: Direct buffer memory

    在NIO程序经常使用BByteuffer来读取或写入数据，这是一个基于通道与缓冲区的I/O方式
    可以使用Native函数直接分配堆外内存，然后通过Java堆里面的DirectoryBuffer对象作为这快内存的引用进行操作，
    这样能在一些场景中显著提高性能，因为避免了在Java堆和Native堆中来回复制数据
 */
public class DirectBufferMemoryDemo {
    public static void main(String[] args) {
        System.out.println("配置的MaxDirectMemory大小:"+sun.misc.VM.maxDirectMemory()/(double)1024/1024+"MB");
        try {
            Thread.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(6*1024*1024);//这里要求获取的本地内存为6M>5M 抛出OOM异常
    }
}
