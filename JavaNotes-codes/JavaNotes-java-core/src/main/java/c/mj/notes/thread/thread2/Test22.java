package c.mj.notes.thread.thread2;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.ReentrantLock;

/**
 * create class Test22.java @version 1.0.0 by @author ChenMJ @date 2022-01-14 14:31:00
 */
@Slf4j(topic = "C.MJ.NOTES")
public class Test22 {
    private static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(()->{
            try {
                log.debug("尝试获取锁");
                lock.lockInterruptibly();
            } catch (InterruptedException e) {
                e.printStackTrace();
                log.debug("没有获取锁");
                return;
            }
            try{
                log.debug("获取到锁");
            }finally {
                lock.unlock();
            }
        },"t1");

        lock.lock();
        t1.start();

        Thread.sleep(1000);
        log.debug("打断t1");
        t1.interrupt();
    }
}
