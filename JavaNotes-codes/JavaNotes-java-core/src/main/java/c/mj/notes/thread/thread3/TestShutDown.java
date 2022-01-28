package c.mj.notes.thread.thread3;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * create class TestShutDown.java @version 1.0.0 by @author ChenMJ @date 2022-01-24 14:32:00
 */
@Slf4j(topic = "C.MJ.NOTES")
public class TestShutDown {
    public static void main(String[] args) {
        ExecutorService pool =  Executors.newFixedThreadPool(2);
        Future<String> future1 = pool.submit(() -> {
            log.debug("task 1 running");
            Thread.sleep(1000);
            log.debug("task 1 finish");
            return "future1";
        });

        Future<String> future2 = pool.submit(() -> {
            log.debug("task 2 running");
            Thread.sleep(1000);
            log.debug("task 2 finish");
            return "future2";
        });

        Future<String> future3 = pool.submit(() -> {
            log.debug("task 3 running");
            Thread.sleep(1000);
            log.debug("task 3 finish");
            return "future3";
        });

        log.debug("shutdown");
        pool.shutdown();

        pool.submit(() -> {
            log.debug("task 4 running");
            Thread.sleep(1000);
            log.debug("task 4 finish");
            return "future4";
        });
    }
}