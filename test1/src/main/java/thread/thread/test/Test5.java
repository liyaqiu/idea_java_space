package thread.thread.test;

import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Test5 {
    public static void main(String[] args) {
        T2 t2 = new T2();
        t2.start();
//        T2 t3 = new T2();
//        t3.start();
        while (true){
            System.out.println(t2.getState());
            if(new Scanner(System.in).next().equals("1")){
                t2.interrupt();
            }
        }

    }
}

class T2 extends  Thread{

    BlockingQueue blockingQueue = new ArrayBlockingQueue(1);

    @Override
    public  void run() {

        while (true){
            try {

                System.out.println("zhixing");
                blockingQueue.take();
            } catch (InterruptedException e) {

            }
        }
    }
}