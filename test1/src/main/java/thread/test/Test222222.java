package thread.test;

import org.openjdk.jol.info.ClassLayout;

import java.util.List;
import java.util.Vector;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;

public class Test222222 {
    public static void main(String[] args) {
        Cat cat = new Cat();

        int i = 0;
        while (true) {
            i++;
            if(i==3){
                System.out.println(cat.hashCode());
            }
            synchronized (cat) {
                System.out.println(ClassLayout.parseInstance(cat).toPrintable().substring(120, 187));
            }
            System.out.println(ClassLayout.parseInstance(cat).toPrintable().substring(120, 187));
            if(i>5){
                break;
            }
        }


    }

    public static void main1(String[] args) throws InterruptedException {

        BlockingQueue b = new ArrayBlockingQueue(1);
        b.take();
        b.put(null);

        List<String> l  = new Vector();



        CountDownLatch lock1 = new CountDownLatch(1);
        CountDownLatch lock2 = new CountDownLatch(1);
        List<Cat> list = new Vector<>();
        List<Cat> list1 = new Vector<>();
        Thread t1 = new Thread(() -> {
            for (int i = 0; i <5 ; i++) {
                Cat cat = new Cat();
                list.add(cat);
                System.out.println("t1-"+i+" "+ClassLayout.parseInstance(cat).toPrintable().substring(120,187));
                synchronized (cat) {
                    try {
                        cat.wait(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("t1-"+i+" "+ClassLayout.parseInstance(cat).toPrintable().substring(120,187));
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("t1-"+i+" "+ClassLayout.parseInstance(cat).toPrintable().substring(120,187));
                System.out.println("--------------------------");
            }
            lock1.countDown();

        }, "T1");
        t1.start();


        Thread t2 = new Thread(() -> {
            try {
                lock1.await();
                lock1.wait(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int i = 0; i <5 ; i++) {
                Cat cat = list.get(i);
                System.out.println("t2-"+i+" "+ClassLayout.parseInstance(cat).toPrintable().substring(120,187));
                synchronized (cat) {
                    System.out.println("t2-"+i+" "+ClassLayout.parseInstance(cat).toPrintable().substring(120,187));
                }
                System.out.println("t2-"+i+" "+ClassLayout.parseInstance(cat).toPrintable().substring(120,187));

                System.out.println("--------------------------");
            }
            lock2.countDown();

        }, "T2");
        t2.start();

//        Thread t3 = new Thread(() -> {
//            try {
//                lock2.await();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            for (int i = 0; i <5 ; i++) {
//                Cat cat = list.get(i);
//                System.out.println("t3-"+i+" "+ClassLayout.parseInstance(cat).toPrintable().substring(120,187));
//                synchronized (cat) {
//                    System.out.println("t3-"+i+" "+ClassLayout.parseInstance(cat).toPrintable().substring(120,187));
//                }
//                System.out.println("t3-"+i+" "+ClassLayout.parseInstance(cat).toPrintable().substring(120,187));
//                System.out.println("--------------------------");
//            }
//        }, "T3");
//        t3.start();
        t2.join();
        System.out.println("------------main--------------");

//        Cat cat = list.get(68);
//        System.out.println("main" +ClassLayout.parseInstance(cat).toPrintable().substring(120,187));
//        synchronized (cat) {
//            System.out.println("main" +ClassLayout.parseInstance(cat).toPrintable().substring(120,187));
//        }
//        System.out.println("main" +ClassLayout.parseInstance(cat).toPrintable().substring(120,187));
        Cat cat111 = new Cat();
        System.out.println(ClassLayout.parseInstance(cat111).toPrintable().substring(120,187));
        System.out.println(ClassLayout.parseInstance(new String()).toPrintable().substring(120,195));
    }
}
class Cat{

}

