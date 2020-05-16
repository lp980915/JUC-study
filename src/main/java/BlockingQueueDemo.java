package main.java;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

//阻塞队列
public class BlockingQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> blockingQueue=new ArrayBlockingQueue<>(3);

      /*  //第一组：add和remove方法（会抛出异常）
        System.out.println(blockingQueue.add("A"));
        System.out.println(blockingQueue.add("B"));
        System.out.println(blockingQueue.add("C"));
        //添加超出限制则抛出异常
        //System.out.println(blockingQueue.add("D"));

        //element方法检查队列第一个元素并返回
        System.out.println(blockingQueue.element());

        //remove方法不写参数则按照队列默认的先进先出进行移除
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        //移除超出限制则抛出异常
        //System.out.println(blockingQueue.remove());*/


       /* //第二组：offer和pill（返回true或false）
        System.out.println(blockingQueue.offer("A"));
        System.out.println(blockingQueue.offer("B"));
        System.out.println(blockingQueue.offer("C"));
        System.out.println(blockingQueue.offer("D"));

        //使用peek检查队列第一个元素
        System.out.println(blockingQueue.peek());

        //移除时没有的值返回null
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());*/


      /*  //第三组：put和take方法，put添加元素时，如果队列已满，则会一直阻塞，直到队列有空位置才会进行添加，
        // 反之take时如果队列为空也会一直阻塞,直到队列中有元素才会进行移除，且put无返回值
        blockingQueue.put("A");
        blockingQueue.put("B");
        blockingQueue.put("C");
        //blockingQueue.put("D");

        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
        //System.out.println(blockingQueue.take());*/


        //第四组：offer(e,time,unit),pull(time,unit)    (超时阻塞队列，只会阻塞一定的时间，时间到了之后该线程会退出)
        //如果队列已满，则阻塞三秒钟，三秒后还是队列还是满的则返回false
        System.out.println(blockingQueue.offer("A", 3L, TimeUnit.SECONDS));
        System.out.println(blockingQueue.offer("B", 3L, TimeUnit.SECONDS));
        System.out.println(blockingQueue.offer("C", 3L, TimeUnit.SECONDS));
        System.out.println(blockingQueue.offer("D", 3L, TimeUnit.SECONDS));

        //移除时同理，只阻塞三秒，三秒后队列依然为空则返回null
        System.out.println(blockingQueue.poll(3L,TimeUnit.SECONDS));
        System.out.println(blockingQueue.poll(3L,TimeUnit.SECONDS));
        System.out.println(blockingQueue.poll(3L,TimeUnit.SECONDS));
        System.out.println(blockingQueue.poll(3L,TimeUnit.SECONDS));
    }
}
