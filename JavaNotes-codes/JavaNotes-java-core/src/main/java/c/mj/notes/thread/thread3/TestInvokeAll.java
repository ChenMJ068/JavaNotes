package c.mj.notes.thread.thread3;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * create class TestInvokeAll.java @version 1.0.0 by @author ChenMJ @date 2022-01-24 14:05:00
 */
@Slf4j(topic = "C.MJ.NOTES")
public class TestInvokeAll {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService pool =  Executors.newFixedThreadPool(2);
        List<Future<String>> futureList = pool.invokeAll(Arrays.asList(
                () -> {
                    log.debug("begin");
                    Thread.sleep(1000);
                    return "1";
                },
                () -> {
                    log.debug("begin");
                    Thread.sleep(1000);
                    return "2";
                },
                () -> {
                    log.debug("begin");
                    Thread.sleep(1000);
                    return "3";
                }
        ));
        futureList.forEach(stringFuture -> {
            try {
                log.debug("{}",stringFuture.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        });
    }
}