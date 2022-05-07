package socket.netty.future;

import io.netty.channel.DefaultEventLoop;
import io.netty.util.concurrent.DefaultPromise;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.Promise;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

@Slf4j
public class NettyPromise {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        DefaultEventLoop eventLoop = new DefaultEventLoop();
        DefaultPromise<String> promise = new DefaultPromise<>(eventLoop);

        new Thread(new Runnable(){
            @Override
            public void run() {
                try {
                    log.debug("执行结果");
                    Thread.sleep(3000);
                    promise.setFailure(new RuntimeException("执行异常.."));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"web线程").start();

        log.debug("等待结果");
        log.debug("结果：{}",promise.get());
    }
}
