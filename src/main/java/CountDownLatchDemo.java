package main.java;

import java.util.concurrent.CountDownLatch;

//要求班长最后走
public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatchDemo countDownLatchDemo=new CountDownLatchDemo();
        countDownLatchDemo.closeDoor();
//        countDownLatchDemo.errorCloseDoor();
    }

    public void closeDoor() throws InterruptedException {
        CountDownLatch countDownLatch=new CountDownLatch(6);
        for (int i = 0; i < 6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName()+"\t离开教室");
                //每次使用countDown使计数器减一
                countDownLatch.countDown();
            }, String.valueOf(i)).start();
        }
        //当countDownLatch计数器为0时，await阻塞的线程会被唤醒，继续执行
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName()+"\t班长锁门");

    }

    public void errorCloseDoor(){
        for (int i = 0; i < 6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName()+"\t离开教室");
                //每次使用countDown使计数器减一
            }, String.valueOf(i)).start();
        }
        //当countDownLatch计数器为0时，await阻塞的线程会被唤醒，继续执行
        System.out.println(Thread.currentThread().getName()+"\t班长锁门");

    }
}


