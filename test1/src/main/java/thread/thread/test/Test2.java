package thread.thread.test;

public class Test2 {
    public static void main(String[] args) {
        Animal1 animal = new Animal1();
        Thread thread1 = new Thread(animal);
        thread1.setName("张三");
        animal.setThread(Thread.currentThread());
        System.out.println(thread1.getState());
        //thread1.setDaemon(true);
        thread1.start();

        System.out.println(thread1.getState());
        for (int i = 0; true ; i++) {
            try {
                Thread.sleep(1000);

//                if(i==4){
//                    Thread.currentThread().wait();
//                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(i+" "+Thread.currentThread().getName());
        }
    }
}


class Animal1 implements Runnable{
    Thread thread;

    public Thread getThread() {
        return thread;
    }

    public void setThread(Thread thread) {
        this.thread = thread;
    }

    @Override
    public void run() {
        for (int i = 0; true ; i++) {
            try {
                System.out.println("当前值: "+i+"  "+Thread.currentThread().getName()+" "+Thread.currentThread().getState());
                System.out.println("主线程状态:"+thread.getState());
                Thread.sleep(1000);
                if(i==4){
                    thread.wait(10000);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}