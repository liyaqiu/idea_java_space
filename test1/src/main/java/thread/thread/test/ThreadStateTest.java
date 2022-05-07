package thread.thread.test;

public class ThreadStateTest {
    public static void main(String[] args) throws InterruptedException {

        //new runnanle  blocked(synchronized)  waiting(join)  timeWaiting(sleep(time) wait(time)) terminated
        ThreadState threadState = new ThreadState();
        threadState.setName("测试线程状态");
        System.out.println("new  :"+threadState.getState());

        threadState.start();
        for (int i = 0; true ; i++) {
            Thread.sleep(2000);
            System.out.println("timed wait :"+threadState.getState());
        }

    }
}

class ThreadState extends Thread {
    @Override
    public void run() {
        for (int i = 0; i<=8 ; i++) {
            System.out.println("runnable :"+Thread.currentThread().getState());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("执行中:" + i);
        }
    }
}
