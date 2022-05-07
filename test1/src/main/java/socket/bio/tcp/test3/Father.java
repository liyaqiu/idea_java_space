package socket.bio.tcp.test3;

public class Father {
    protected synchronized void haha() {
        System.out.println(this.getClass());
        System.out.println("你好" + Thread.currentThread().getName());
        try {
            Thread.sleep(Integer.MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
