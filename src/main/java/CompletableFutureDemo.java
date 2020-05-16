import java.util.concurrent.CompletableFuture;

public class CompletableFutureDemo {
    public static void main(String[] args) throws Exception{
        //CompletableFuture的runAsync方法为无返回值的异步回调
        CompletableFuture<Void> completableFuture=CompletableFuture.runAsync(()->{
            System.out.println(Thread.currentThread().getName()+"该异步回调无返回值");
        });
        //get方法会执
        System.out.println(completableFuture.get());

        //CompletableFuture的supplyAsync方法为有返回值的异步回调。类似ajax的使用方式
        CompletableFuture<String> completableFuture1=CompletableFuture.supplyAsync(()->{
            System.out.println(Thread.currentThread().getName()+"成功执行supplyAsync");
            //int i=10/0;
            //如果调用未出现异常，则get方法会获取到此处的返回值
            return "110";
        });

        System.out.println(completableFuture1.whenComplete((t, r) -> {
            System.out.println("当异步任务未出现异常时可获取到第一个参数:" + t);
            System.out.println("当异步任务出现异常时可获取到第二个参数(异常信息):" + r);
        }).exceptionally((e) -> {
            System.out.println("当异步任务出现异常可使用exceptionally获取到异常信息:" + e);
            //如果调用出现异常，则get方法会获取到此处的返回值
            return "报错了";
            //get方法可以获取到异步回调的返回值。无论是出现异常还是未出现异常
        }).get());
    }
}
