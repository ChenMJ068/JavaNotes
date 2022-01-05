package c.mj.notes.thread.thread2;

import lombok.extern.slf4j.Slf4j;

/**
 * 避免临界区的竞态条件发生
 * - 阻塞式的解决方案：synchronized , lock
 * - 非阻塞式的解决方案：原子变量
 * create class Test1.java @version 1.0.0 by @author ChenMJ @date 2022-01-04 16:59:00
 */
@Slf4j(topic = "C.MJ.NOTES")
public class Test1 {

    private static int counter = 0;
    static Object lock = new Object();
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(()->{
            for (int i = 0; i < 5000; i++) {
                synchronized (lock){
                    counter++;
                }
            }
        },"t1");


        Thread t2 = new Thread(()->{
            for (int i = 0; i < 5000; i++) {
                synchronized (lock){
                    counter--;
                }
            }
        },"t2");

        t1.start();
        //log.debug("counter --1 : {}",counter);
        t2.start();
        //log.debug("counter --2 : {}",counter);
        t1.join();
        //log.debug("counter --3 : {}",counter);
        t2.join();
        log.debug("counter --4 : {}",counter);
    }
}