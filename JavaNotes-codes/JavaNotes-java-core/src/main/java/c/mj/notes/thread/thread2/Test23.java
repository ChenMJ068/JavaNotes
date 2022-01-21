package c.mj.notes.thread.thread2;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.ReentrantLock;

/**
 * create class Test23.java @version 1.0.0 by @author ChenMJ @date 2022-01-14 14:37:00
 */
@Slf4j(topic = "C.MJ.NOTES")
public class Test23 {
    private static ReentrantLock lock = new ReentrantLock();
    public static void main(String[] args) {
        Thread t1 = new Thread(()->{
            log.debug("尝试获取锁");
            if (!lock.tryLock()){
                log.debug("获取不到锁");
                return;
            }
            try{
                log.debug("获取到了锁");
            }finally {
                lock.unlock();
            }
        },"t1");

        lock.lock();
        t1.start();
    }
}