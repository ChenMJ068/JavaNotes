package c.mj.notes.thread.thread3;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * create class TestInvokeAny.java @version 1.0.0 by @author ChenMJ @date 2022-01-24 14:14:00
 */
@Slf4j(topic = "C.MJ.NOTES")
public class TestInvokeAny {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService pool =  Executors.newFixedThreadPool(2);
        String any = pool.invokeAny(Arrays.asList(
                () -> {
                    log.debug("begin");
                    Thread.sleep(1000);
                    return "1";
                },
                () -> {
                    log.debug("begin");
                    Thread.sleep(500);
                    return "2";
                },
                () -> {
                    log.debug("begin");
                    Thread.sleep(1000);
                    return "3";
                }
        ));

        log.debug("{}",any);

    }
}