package c.mj.notes.thread.thread2;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.LockSupport;

/**
 * create class Test7.java @version 1.0.0 by @author ChenMJ @date 2022-01-12 15:45:00
 */
@Slf4j(topic = "C.MJ.NOTES")
public class Test7 {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() ->{
            log.debug("begin....");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.debug("park....");
            LockSupport.park();
            log.debug("resume....");
        },"t1");

        t1.start();
        Thread.sleep(1000);
        log.debug("unpark...");
        LockSupport.unpark(t1);
    }
}