package thread.thread.test;

public class Test3 {
    public static void main(String[] args) throws InterruptedException {
        T1 t1 = new T1();
        T1 t2 = new T1();
        t1.start();
        t2.start();
        for (int i = 0; true; i++) {
            Thread.sleep(1000);
            System.out.println("t1"+t1.getName()+t1.getState());
            System.out.println("t2"+t2.getName()+t2.getState());
        }
    }
}

class T1 extends Thread{

    static void haha(){
        synchronized (T1.class) {
            System.out.println("11111");
            try {
                Thread.currentThread().wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void run() {
        for (int i = 0; true ; i++) {
            T1.haha();
        }
    }
}
