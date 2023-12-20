package thread.thread.test;

import static java.lang.Thread.sleep;
import static java.util.concurrent.TimeUnit.NANOSECONDS;

public class Test7 {
     static int a = 100;
    public static void main(String[] args) throws InterruptedException {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                    System.out.println("test1");
                    try {
                        a = 1000;
                        sleep(1000000);
                    } catch (InterruptedException e) {

                    }

            }
        }, "test1");
        thread.setPriority(1);
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    System.out.println("test2");
                    try {
                        sleep(1000);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "test2");
        thread2.setPriority(10);
        thread2.start();
        thread.start();
        sleep(3000);
        thread.interrupt();


        while (true){

            NANOSECONDS.sleep(1);
            System.out.println(thread2.getName()+"  "+thread2.isInterrupted());
            System.out.println(thread.getName()+"  "+thread.isInterrupted()+"  "+thread.getState());
        }

    }

}
