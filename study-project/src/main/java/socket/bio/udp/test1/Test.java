package socket.bio.udp.test1;





import socket.bio.tcp.test3.Son;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test {
    public static void main(String[] args) throws Exception {
        ExecutorService service = Executors.newFixedThreadPool(2);

        Son son = new Son();
        service.execute(new Runnable() {
            @Override
            public void run() {
                while (true){
                    son.haha();
                }
            }
        });

        service.execute(new Runnable() {
            @Override
            public void run() {
                while (true){
                    son.haha();
                }
            }
        });
    }
}
