package c.mj.notes.thread.thread3;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * create class TestSubmit.java @version 1.0.0 by @author ChenMJ @date 2022-01-24 13:58:00
 */
@Slf4j(topic = "C.MJ.NOTES")
public class TestSubmit {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService pool =  Executors.newFixedThreadPool(2);
        Future<String> future = pool.submit(() -> {
            log.debug("running");
            Thread.sleep(1000);
            return "OK";
        });
        ConcurrentHashMap<Object, Object> hashMap = new ConcurrentHashMap<>();
        log.debug("{}",future.get());
    }
}