package thread.thread.test;
//正常的线程如果被thread.interrupt();，那么Thread.currentThread().isInterrupted() 返回true
//Thread.currentThread().isInterrupted() 默认返回false
// 或者是遇到sleep join wait 被thread.interrupt() 也会返回false
// Thread.interrupted()如果调用此方法输出一次后会清除标记，同时Thread.currentThread().isInterrupted() 也会跟着被清除
public class Test8 {

    public static void main(String[] args) throws InterruptedException {

        Thread thread = new Thread(new Runnable(){
            @Override
            public void run() {
               while (true){
                   try {
                       System.out.println("睡眠");
                       Thread.sleep(100000);
                       System.out.println("我还要执行");
                   } catch (InterruptedException e) {
                       e.printStackTrace();
                       System.out.println("sleep join wait 打断了"+Thread.currentThread().isInterrupted());
                       Thread.currentThread().interrupt();
                   }
                   if(Thread.currentThread().isInterrupted()){
                       System.out.println("正常被打断了"+Thread.currentThread().isInterrupted());
                       break;
                   }
               }
            }
        });
        thread.start();
        Thread.sleep(3000);
        System.out.println("打断");
        thread.interrupt();

        System.out.println("打断成功");

    }
}
