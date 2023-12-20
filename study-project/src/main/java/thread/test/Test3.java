package thread.test;

import java.util.Scanner;
import java.util.concurrent.locks.LockSupport;

public class Test3 {
    public static void main(String[] args) throws InterruptedException {


        Thread main = Thread.currentThread();

        final Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    System.out.println(Thread.interrupted());

                    LockSupport.park();
                }

                //System.out.println("线程1"+Thread.currentThread().isInterrupted());
                //如果Thread.interrupted()为true。不管有没有许可都会运行
                //如果Thread.interrupted()为false，没有许可就会阻塞
                LockSupport.park();
                System.out.println("线程1执行完1");
                System.out.println(Thread.interrupted());
                LockSupport.park();
                System.out.println("线程1执行完2");
                System.out.println(Thread.interrupted());
                LockSupport.park();
                System.out.println("线程1执行完3");
                System.out.println(Thread.interrupted());
            }
        });
        t1.start();
        t1.setPriority(10);
        //t1.interrupt();
        //LockSupport.unpark(t1);
        System.out.println("main");
        while (true){
            String s = new Scanner(System.in).next();
            if(s.equals("1")){
                t1.interrupt();
            }else{
                LockSupport.unpark(t1);
            }


        }




    }
}
