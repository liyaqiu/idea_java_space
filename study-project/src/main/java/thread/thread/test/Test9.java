package thread.thread.test;

import java.util.concurrent.locks.LockSupport;
//Thread.interrupted() 重置标记 Thread.currentThread().isInterrupted() 不会重置标记
//LockSupport.park();  park遇到 interrupted值为false的时候会生效
public class Test9 {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new Runnable(){
            @Override
            public void run() {
                System.out.println("1"+Thread.interrupted());
                LockSupport.park();
                System.out.println("park 1");
                System.out.println("2"+Thread.interrupted());
                LockSupport.park();
                System.out.println("park 2");
                System.out.println("3"+Thread.currentThread().isInterrupted());
            }
        });
        thread.start();
        Thread.sleep(5000);
        System.out.println(thread.getState());
        thread.interrupt();
        System.out.println("unpark");
        Thread.sleep(5000);
        thread.interrupt();
    }
}
