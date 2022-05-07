package socket.netty.version1;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicReference;

public class AsynTest {
    public static void main(String[] args) throws InterruptedException {



        Mytherd mytherd = new Mytherd();
        mytherd.start();
        AtomicReference<String> stringAtomicReference = new AtomicReference<>();
        mytherd.strt(new Message() {
            @Override
            public void getMessage(String msg) {
                stringAtomicReference.set(msg);

            }
        });
       while (true){
           if(stringAtomicReference.get()==null){
               Thread.sleep(1000);
               System.out.println("客户端还没返回来结果");
           }else{
               System.out.println(stringAtomicReference.get());
               System.exit(0);
           }
       }

    }
}
interface Message{
    void getMessage(String msg);
}

class Mytherd extends Thread{

    BlockingQueue<Message> queue = new LinkedBlockingQueue<>();

    public void strt(Message message){
        queue.add(message);
    }
    @Override
    public void run() {
        while (true){
            try {
                Message message = queue.take();
                Thread.sleep(5000);
                System.out.println("我取到了消息");
                message.getMessage("hello");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
