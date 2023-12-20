package ideaa;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class Test1 {

    public static void main(String[] args) {




        Set<String> list = ConcurrentHashMap.newKeySet();
        //Set<String> list = Sets.newConcurrentHashSet();

        list.add("11");
        list.add("22");
        list.add("33");

        ExecutorService executorService = Executors.newFixedThreadPool(3);
        executorService.execute(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                Thread.sleep(1000);

                list.remove("33");
                list.add("555");
                list.add("666");
                list.add("777");
            }
        });
        executorService.execute(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                for (String s : list) {
                    log.error("{}", s);
                    Thread.sleep(1000);
                }
            }
        });

    }

}
