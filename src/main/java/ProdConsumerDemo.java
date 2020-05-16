package main.java;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//生产者消费者
//高内聚，低耦合的情况下，线程操作资源类
//判断，干活，通知
//防止虚假唤醒，使用while循环进行判断
//多线程的调用和通信(wait和notify之类)不能使用if判断，必须使用while,因为notify唤醒之后线程必须得回去重新判断一次！！
public class ProdConsumerDemo {
    public static void main(String[] args) {
        Aircondition aircondition=new Aircondition();
        new Thread(() -> {
            try {
                for (int i = 1; i <=10; i++) {
                    aircondition.incr();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "A").start();

        new Thread(() -> {
            try {
                for (int i = 1; i <=10; i++) {
                    aircondition.decr();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "B").start();

        new Thread(() -> {
            try {
                for (int i = 1; i <=10; i++) {
                    aircondition.incr();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "C").start();

        new Thread(() -> {
            try {
                for (int i = 1; i <=10; i++) {
                    aircondition.decr();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "D").start();
    }

}

class Aircondition{
    //flag既是初始变量，又是标志位
    //lock版本，无法使用wait和notify方法，需要新建condition对象使用await和signal方法
    private int flag=0;
    private Lock lock=new ReentrantLock();
    private Condition condition=lock.newCondition();

    public void incr() throws InterruptedException {

        lock.lock();
        try {
            //判断
            //多线程的调用和通信(wait和notify之类)不能使用if判断，必须使用while,因为notify唤醒之后线程又得回去重新判断一次
            // if(flag!=0)
            while(flag!=0){
                condition.await();
            }
            //干活
            flag++;
            System.out.println(Thread.currentThread().getName()+"\t"+flag);
            //通知
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void decr() throws InterruptedException {

        lock.lock();
        try {
            //判断
            //多线程的调用和通信(wait和notify之类)不能使用if判断，必须使用while,因为notify唤醒之后线程又得回去重新判断一次
            // if(flag!=0)
            while(flag==0){
                condition.await();
            }
            //干活
            flag--;
            System.out.println(Thread.currentThread().getName()+"\t"+flag);
            //通知
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

    //synchronized版本，使用wait和notify方法
//    public synchronized void incr() throws InterruptedException {
//        //判断
//        //多线程的调用和通信(wait和notify之类)不能使用if判断，必须使用while,因为notify唤醒之后线程又得回去重新判断一次
         //flag既是初始变量，又是标志位
//        // if(flag!=0)
//        while(flag!=0){
//            this.wait();
//        }
//        //干活
//        flag++;
//        System.out.println(Thread.currentThread().getName()+"\t"+flag);
//        //通知
//        this.notifyAll();
//    }
//
//    public synchronized void decr() throws InterruptedException {
//        //判断
//        //多线程的调用和通信(wait和notify之类)不能使用if判断，必须使用while,因为notify唤醒之后线程又得回去重新判断一次
//        // if(flag!=0)
//        while(flag==0){
//            this.wait();
//        }
//        //干活
//        flag--;
//        System.out.println(Thread.currentThread().getName()+"\t"+flag);
//        //通知
//        this.notifyAll();
//    }
}
