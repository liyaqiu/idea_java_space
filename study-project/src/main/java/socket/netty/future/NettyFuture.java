package socket.netty.future;

import io.netty.channel.DefaultEventLoop;
import io.netty.util.concurrent.Future;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@Slf4j
public class NettyFuture {

    public static void main1(String[] args) throws InterruptedException, ExecutionException {
        DefaultEventLoop defaultEventLoop = new DefaultEventLoop();
        Future<String> future = defaultEventLoop.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                log.debug("执行结果");
                Thread.sleep(3000);
                return "hello";
            }
        });
        log.debug("等待结果");
        log.debug("结果:{}",future.get());
        defaultEventLoop.shutdownGracefully();
    }
    public static void main(String[] args) throws InterruptedException, ExecutionException, IOException {
        DefaultEventLoop defaultEventLoop = new DefaultEventLoop();
        defaultEventLoop.schedule(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                log.debug("3执行结果");
                Thread.sleep(10000);
                log.debug("3处理完毕");
            }
        }, 0, TimeUnit.SECONDS);
        defaultEventLoop.execute(new Runnable() {
            @Override
            public void run() {
                log.debug("1执行结果");
            }
        });
        defaultEventLoop.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                log.debug("2执行结果");
                return "hello";
            }
        });
        while (true){
            System.in.read();
            defaultEventLoop.submit(new Callable<String>() {
                @Override
                public String call() throws Exception {
                    log.debug("2执行结果");
                    return "hello";
                }
            });
        }
    }
}
