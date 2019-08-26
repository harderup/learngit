package OutOfMemoryError;

import java.util.concurrent.TimeUnit;

/*
    高并发请求服务器时，经常 出现如此啊异常：Java.lang.OutOfMemoryError:unbale to create new native thread
    准确的讲该native thread 异常与对应的平台有关

    导致原因：
    你的应用创建太多的线程，一个应用进程创建多个线程，超过系统承载极限
    你的服务器不允许你的应用程序创建这么多的线程，linux系统默认允许单个进程可以创建的线程数是1024个
    超过这个数量就会报错

    解决办法：
    想办法降低你应用程序创建线程的数量，分析应用是否真的需要创建这么多线程，如果不是，改代码将线程数降到最低
    对于有的应用，确实需要创建很多线程，远超过linux系统的默认1024个线程的限制，可以通过linux服务器配置，扩大linux默认限制
 */

public class UnableCreateNewThreadDemo {
    public static void main(String[] args) {
        for(int i = 0; ; i++){
            new Thread(()->{
                try {
                    TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
