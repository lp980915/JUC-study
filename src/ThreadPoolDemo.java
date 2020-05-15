import java.util.concurrent.*;

public class ThreadPoolDemo {
    public static void main(String[] args) {
        //获取当前电脑cpu逻辑核心数
        System.out.println(Runtime.getRuntime().availableProcessors());
        ExecutorService threadPool=new ThreadPoolExecutor(
                2,
                Runtime.getRuntime().availableProcessors()+1,
                3L,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(3),
                Executors.defaultThreadFactory(),
                //回退给原线程（main）来执行
                new ThreadPoolExecutor.AbortPolicy());
        //回退给原线程执行（main线程）
        //new ThreadPoolExecutor.CallerRunsPolicy()
        //默认的方式，报异常的方式
        //new ThreadPoolExecutor.AbortPolicy();
        //抛弃队列中等待最久的线程
        //new ThreadPoolExecutor.DiscardOldestPolicy();
        //直接抛弃多余线程，不会报错
        //new ThreadPoolExecutor.DiscardPolicy();

        try {
            for (int i = 1; i <= 10; i++) {
                threadPool.execute(()->{
                    System.out.println(Thread.currentThread().getName()+"\t线程办理了业务");
                });
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            threadPool.shutdown();
        }
    }


    //JDK提供的线程池创建方法，不推荐使用
    public static void initPool() {
        //线程池的工具类Executors,类似于collections为collection的工具类，都是以s结尾
        //ExecutorService threadPool = Executors.newFixedThreadPool(5); //代表一个线程池有五个受理线程,模拟五个银行业务窗口
        //ExecutorService threadPool=Executors.newSingleThreadExecutor();//代表单个受理线程的线程池
        ExecutorService threadPool= Executors.newCachedThreadPool();//代表一个线程池有N个线程，可扩展，可伸缩
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
