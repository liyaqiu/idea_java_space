package socket.netty.future;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Slf4j
public class JDKFuture {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(1);
        Future<String> future = service.submit(() -> {
            Thread.sleep(3000);
            log.debug("返回结果");
            return "hello";
        });

        log.debug("等待结果");
        log.debug("结果:{}",future.get());
        service.shutdown();
    }
}
