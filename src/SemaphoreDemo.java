import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

//Semaphore的作用类似于可以锁住多个线程的synchronized
public class SemaphoreDemo {
    public static void main(String[] args) {
        //设定资源并发线程数为3，一次只能有三个线程进入，等线程释放了资源后其他线程才可以执行
        Semaphore semaphore=new Semaphore(3);
        for (int i = 1; i <= 6; i++) {
            new Thread(()->{
                try {
                    //抢占资源
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName()+"号车抢到了车位");
                    TimeUnit.SECONDS.sleep(3);
                    System.out.println(Thread.currentThread().getName()+"号车离开了车位");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    //释放资源
                    semaphore.release();
                }

            },String.valueOf(i)).start();
        }
    }
}
