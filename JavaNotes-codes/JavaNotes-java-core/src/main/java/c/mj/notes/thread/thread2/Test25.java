package c.mj.notes.thread.thread2;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.LockSupport;

/**
 * create class Test25.java @version 1.0.0 by @author ChenMJ @date 2022-01-17 13:54:00
 */
@Slf4j(topic = "C.MJ.NOTES")
public class Test25 {
    public static void main(String[] args) {
        Thread t1 = new Thread(()->{
            LockSupport.park();
            log.debug("t1");
        },"t1");
        t1.start();
        Thread t2 = new Thread(()->{
            log.debug("t2");
            LockSupport.unpark(t1);
        },"t2");

        t2.start();
    }
}
