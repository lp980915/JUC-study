import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * ForkJoinPool可以将任务划分为多个，比如要算从0加到100，可以将任务分成两部分，一部分从0加到50，另一部分从51加到100（fork），
 * 然后将任务再划分，将50分为0-25和26-50，以此类推，直到两数的差距小于等于十，此时开始进行运算，再将分割成的多个任务的计算结果逐一返回（join）
 * 该案例为对两个数之间所有元素的和进行计算
 */
public class ForkJoinPoolDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        MyTask myTask=new MyTask(0,50);
        ForkJoinPool forkJoinPool=new ForkJoinPool();
        //获取了任务的返回值，直接get
        ForkJoinTask<Integer> forkJoinTask = forkJoinPool.submit(myTask);
        System.out.println(forkJoinTask.get());
        forkJoinPool.shutdown();
    }
}

class MyTask extends RecursiveTask<Integer>{

    private static final Integer ADJUST_VALUE=10;
    private int begin;
    private int end;
    private int result;

    public MyTask(int begin,int end){
        this.begin=begin;
        this.end=end;
    }
    @Override
    protected Integer compute() {
        //如果一次任务的数量小于等于10，那么只需要单次执行即可
        if(end-begin<=ADJUST_VALUE){
            for (int i = begin; i <= end; i++) {
                result=result+i;
            }
        }else{
            //取得中间值
            int middle=(end+begin)/2;
            //小于中间值的部分
            MyTask task1=new MyTask(begin,middle);
            //大于中间值的部分
            MyTask task2=new MyTask(middle+1,end);
            //再次分割（再次调用compute方法，递归）
            task1.fork();
            //再次分割（再次调用compute方法，递归）
            task2.fork();
            //将分割的任务结果返回并相加
            result=task1.join()+task2.join();
        }

        return result;
    }
}
