import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//AA打印五次，BB打印10次，CC打印15次，依次来十轮
public class ConditionDemo {
    public static void main(String[] args) {
        ABC abc = new ABC();
        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                abc.print();
            }
        }, "AA").start();

        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                abc.print();
            }
        }, "BB").start();

        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                abc.print();
            }
        }, "CC").start();
    }

}

class ABC{
    private Lock lock=new ReentrantLock();
    private int flag=1;
    Condition c1=lock.newCondition();
    Condition c2=lock.newCondition();
    Condition c3=lock.newCondition();
    public void print5(){

            lock.lock();
            try {
                //判断
                while (flag!=1){
                    c1.await();
                }
                //干活
                for (int i = 1; i <= 5; i++) {
                    System.out.println(i+"--"+Thread.currentThread().getName());
                }
                //修改标志位
                flag=2;
                //唤醒c2
                c2.signal();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }


    }

    public void print10(){

        lock.lock();
        try {
            //判断
            while (flag!=2){
                c2.await();
            }
            //干活
            for (int i = 1; i <= 10; i++) {
                System.out.println(i+"--"+Thread.currentThread().getName());
            }
            //修改标志位
            flag=3;
            //唤醒c2
            c3.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void print15(){

        lock.lock();
        try {
            //判断
            while (flag!=3){
                c3.await();
            }
            //干活
            for (int i = 1; i <= 15; i++) {
                System.out.println(i+"--"+Thread.currentThread().getName());
            }
            //修改标志位
            flag=1;
            //唤醒c2
            c1.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

    public void print(){

    if ("AA".equals(Thread.currentThread().getName())){

    lock.lock();
    try {
        //判断
        while (flag!=1){
            c1.await();
        }
        //干活
        for (int i = 1; i <= 5; i++) {
            System.out.println(i+"--"+Thread.currentThread().getName());
        }
        //修改标志位
        flag=2;
        //唤醒c2
        c2.signal();
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        lock.unlock();
    }

   }


        if ("BB".equals(Thread.currentThread().getName())) {
            lock.lock();
            try {
                //判断
                while (flag != 2) {
                    c2.await();
                }
                //干活
                for (int i = 1; i <= 10; i++) {
                    System.out.println(i + "--" + Thread.currentThread().getName());
                }
                //修改标志位
                flag = 3;
                //唤醒c2
                c3.signal();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

        if ("CC".equals(Thread.currentThread().getName())) {
            lock.lock();
            try {
                //判断
                while (flag != 3) {
                    c3.await();
                }
                //干活
                for (int i = 1; i <= 15; i++) {
                    System.out.println(i + "--" + Thread.currentThread().getName());
                }
                //修改标志位
                flag = 1;
                //唤醒c2
                c1.signal();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }
}
