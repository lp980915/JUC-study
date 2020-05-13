import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadPoolDemo {
    public static void main(String[] args) {
        //线程池的工具类Executors,类似于collections为collection的工具类，都是以s结尾
        //ExecutorService threadPool = Executors.newFixedThreadPool(5); //代表一个线程池有五个受理线程,模拟五个银行业务窗口
        //ExecutorService threadPool=Executors.newSingleThreadExecutor();//代表单个受理线程的线程池
        ExecutorService threadPool=Executors.newCachedThreadPool();//代表一个线程池有N个线程，可扩展，可伸缩
        //由于线程池的每个线程执行完成后都需要释放资源并回到线程池，所以需要try catch finally块
        try {
            //模拟十个线程需要办理业务，体现了线程池中对线程的复用
            for (int i = 1; i <= 10; i++) {
                threadPool.execute(()->{
                    System.out.println(Thread.currentThread().getName()+"\t线程办理了业务");
                });
               //TimeUnit.MILLISECONDS.sleep(500);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            threadPool.shutdown();
        }

    }
}
