package thread.thread.test;

import java.util.Scanner;

public class Test4 {
    public static void main(String[] args) throws InterruptedException {
        WaitThread w1 = new WaitThread();

        Thread thread1 = new Thread(w1);
        thread1.setName("thread1");
        Thread thread2 = new Thread(w1);
        thread2.setName("thread2");
        thread1.start();
       // thread2.start();
//        Thread thread3 = new Thread(w1);
//        thread3.setName("thread3");

//
//        Thread.sleep(7000);
//
//        while (true){
//            Thread.sleep(1000);
//            System.out.println("thread1:"+thread1.getState());
//            System.out.println("thread2:"+thread2.getState());
//
//
//        }
    }
}

class WaitThread implements Runnable{


    static boolean bl =true;
      static void share() throws InterruptedException {
          synchronized(WaitThread.class){
              if("thread1".equals(Thread.currentThread().getName())){
                  System.out.println(new Scanner(System.in).next());
//                  Thread.currentThread().stop();
//                  System.out.println("当前线程进入休眠期:"+Thread.currentThread().getName());
//                  Thread.sleep(5000);
//                  WaitThread.class.notify();
//                  WaitThread.class.wait(200000);
//                  System.out.println("111111111111111111111111111");
              }

              if("thread2".equals(Thread.currentThread().getName())){
                  System.out.println("当前线程进入休眠期:"+Thread.currentThread().getName());
                  Thread.sleep(5000);
                  WaitThread.class.notify();

                  WaitThread.class.wait(200000);
                  System.out.println("2222222222222222222222222");
              }
              if("thread3".equals(Thread.currentThread().getName())){
                  if(bl){
                      Thread.sleep(5000);
                      System.out.println("唤醒管理员----------:"+Thread.currentThread().getName());
                      WaitThread.class.notifyAll();


                  }

              }
          }


    }



    @Override
    public void run() {
        while (true){
            try {
                if(!"thread3".equals(Thread.currentThread().getName())){
                    for (int i = 0; i <= 5; i++) {
                        Thread.sleep(1000);
                        System.out.println("当前线程:" + Thread.currentThread().getName() + " " + i);
                    }
                }

                share();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
