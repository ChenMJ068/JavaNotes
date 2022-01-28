package c.mj.notes.thread.thread3;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * create class TestThreadPoolExecutors.java @version 1.0.0 by @author ChenMJ @date 2022-01-24 10:27:00
 */
@Slf4j(topic = "C.MJ.NOTES")
public class TestThreadPoolExecutors {
    public static void main(String[] args) {
        //newFixedThreadPool();
        //newCacheThreadPool();
        newSingleThreadPool();
    }

    /**
     * 单线程线程池，1个核心线程，1个救急线程，任务对于1时会放入无界队列中，任务执行结束后，这个唯一线程不会被释放。
     * 可以执行串行任务
     *
     */
    private static void newSingleThreadPool(){
        ExecutorService pool = Executors.newSingleThreadExecutor();

        pool.execute(()->{
            log.debug("1");
            int i=1/0;
        });

        pool.execute(()->{
            log.debug("2");
        });

        pool.execute(()->{
            log.debug("3");
        });
    }

    /**
     * 带缓存的线程池
     * 全部都是救急线程，核心线程数0
     */
    private static void newCacheThreadPool(){
        SynchronousQueue<Integer> integers = new SynchronousQueue<>();
        new Thread(()->{
            try {
                log.debug("putting {}",1);
                integers.put(1);
                log.debug("{} putted ",1);

                log.debug("putting...{}",2);
                integers.put(2);
                log.debug("{} putted...",2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"t1").start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(()->{
            try {
                log.debug("tasking {}",1);
                integers.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"t2").start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(()->{
            try {
                log.debug("tasking...{}",2);
                integers.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"t3").start();
    }

    /**
     * 固定大小线程池
     */
    private static void newFixedThreadPool(){
       // ExecutorService pool =  Executors.newFixedThreadPool(2);
        ExecutorService pool =  Executors.newFixedThreadPool(2, new ThreadFactory() {
            private AtomicInteger t = new AtomicInteger(1);

            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r,"myPool_t"+t.getAndIncrement());
            }
        });
        pool.execute(()->{
            log.debug("----1----");
        });
        pool.execute(()->{
            log.debug("----2----");
        });
        pool.execute(()->{
            log.debug("----3----");
        });
        pool.shutdown();
    }
}