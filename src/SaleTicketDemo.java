
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//lock可复用锁
public class SaleTicketDemo {
    public static void main(String[] args) {
        Ticket ticket = new Ticket();
        //new一个runnable接口，属于匿名内部类
      /*  new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i < 40; i++) {
                    ticket.sale();
                }
            }
        }, "A").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i < 40; i++) {
                    ticket.sale();
                }
            }
        }, "B").start();
        */

        //使用lambda表达式
        new Thread(() -> {
            for (int i = 1; i < 40; i++) {
            ticket.sale();
        }
        }, "A").start();

        new Thread(() -> {
            for (int i = 1; i < 40; i++) {
            ticket.sale();
        }
        }, "B").start();

        new Thread(() -> {
            for (int i = 1; i < 40; i++) {
            ticket.sale();
        }
        }, "C").start();
    }
}

class Ticket {
    private Integer number =30;
    Lock lock=new ReentrantLock();
    public void sale(){
        lock.lock();
        try {
            if(number >0){
                System.out.println(Thread.currentThread().getName()+"\t卖出第："+(number--)+"\t张票，还剩下"+(number));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
