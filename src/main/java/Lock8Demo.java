package main.java;

import java.util.concurrent.TimeUnit;
//8种题目，理解锁
public class Lock8Demo {
    public static void main(String[] args) throws InterruptedException {
        Phone phone = new Phone();
        Phone phone1 = new Phone();
        new Thread(() -> {
            try {
                phone.sendEmail();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "老大").start();
        TimeUnit.MILLISECONDS.sleep(100);
        new Thread(() -> {
            //phone1.sayHello();
            //phone.sayHello();
            phone1.sendSMS();
        }, "老二").start();
    }
}

class Phone{
    public static synchronized void sendEmail() throws InterruptedException {
        //JUC中用来替代Thread.sleep的方法，可以定义秒，微秒，小时等等单位

        TimeUnit.SECONDS.sleep(4);
        System.out.println("****sendEmail");
    }
    public synchronized void sendSMS(){
        System.out.println("****sendSMS");
    }

    public void sayHello(){
        System.out.println("****sayHello");
    }
}
