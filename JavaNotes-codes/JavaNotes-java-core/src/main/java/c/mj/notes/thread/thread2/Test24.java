package c.mj.notes.thread.thread2;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * create class Test24.java @version 1.0.0 by @author ChenMJ @date 2022-01-17 10:56:00
 */
@Slf4j(topic = "C.MJ.NOTES")
public class Test24 {
    static final Object lock = new Object();
    static boolean t2runned = false;//表示t2是否运行过
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(()->{
            synchronized (lock){
                while (!t2runned){
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                log.debug("--this`s t1--");
            }
        },"t1");

        Thread t2 = new Thread(()->{
            synchronized (lock){
                log.debug("--this`s t2--");
                t2runned = true;
                lock.notify();
            }

        },"t2");

        t1.start();
        t2.start();
    }
}