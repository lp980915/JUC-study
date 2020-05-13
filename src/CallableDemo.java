import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

//使用继承Callable的方式
//一般将futureTask的get方法放在程序最后边，不然程序中途会等待callable线程的完成而引起阻塞
public class CallableDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //由于Thread（）需要传入runnable接口的实现类，而FutureTask又实现了runnable接口，所以传入futureTask即可，在FutureTask的构造器中传入写好的继承了Callable接口的类
        FutureTask<String> futureTask=new FutureTask<>(new MyThread());
        new Thread(futureTask, "AA").start();
        //同一个futureTask对象，两个线程也是只会调用一次
        new Thread(futureTask,"BB").start();
        System.out.println("我是main线程的");
        //通过futureTask的get方法获取到callable方法的返回值
        String result = futureTask.get();
        System.out.println(result);

    }
}

//callable接口具有泛型，具有返回值，泛型与返回值类型一致，且有可能抛异常
class MyThread implements Callable<String>{

    @Override
    public String call() throws Exception {
        System.out.println("come in call method"+Thread.currentThread().getName());
        TimeUnit.SECONDS.sleep(4);
        return "我是callable";
    }
}


class MyThread1 implements Runnable{

    @Override
    public void run() {

    }
}
