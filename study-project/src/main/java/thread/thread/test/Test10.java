package thread.thread.test;

public class Test10 {

    public static void main(String[] args) {
        Son son = new Son();
        Thread t1 = new Thread(son);
        t1.setName("t1");
        t1.start();


    }

}


class Son implements Runnable {
    int counter = 0;

    synchronized void  decrement() {
        synchronized (this){
            synchronized (this){
                counter++;
            }
        }

        increment();
    }

    synchronized void increment(){
      counter--;
    }

    @Override
    public void run() {
        decrement();
        System.out.println(counter);
    }
}
