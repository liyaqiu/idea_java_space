package thread.thread.test;

import java.util.Scanner;

public class Test1 {
    public static void main(String[] args) {
        Animal animal = new Animal();
        Thread thread1 = new Thread(animal);
        thread1.setName("张三");
        animal.setThread1(thread1);
        thread1.start();


        Scanner scanner = new Scanner(System.in);
        System.out.println(scanner.next());
        thread1.interrupt();
    }
}


class Animal implements Runnable{
    Thread thread1;
    Thread thread2;

    public Thread getThread1() {
        return thread1;
    }

    public void setThread1(Thread thread1) {
        this.thread1 = thread1;
    }

    public Thread getThread2() {
        return thread2;
    }

    public void setThread2(Thread thread2) {
        this.thread2 = thread2;
    }

    @Override
    public void run() {
        for (int i = 0; i< 10 ; i++) {
            try {
                if("张三".equals(Thread.currentThread().getName())){
                    Thread.yield();
                }
                System.out.println("当前值: "+i+"  "+Thread.currentThread().getName());


            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}