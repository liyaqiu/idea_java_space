package thread.thread.test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class Test6 {
    public static void main(String[] args) {
        FutureTask<Integer> futureTask = new FutureTask<>(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                System.out.println("exec.....");
                System.out.println(1 / 0);
                Thread.sleep(10000);
                return 1000;
            }
        });
        Thread thread = new Thread(futureTask, "线程1");
        thread.start();

        System.out.println("开始打赢");
        try {
            System.out.println(futureTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("打印结束");




    }

}
