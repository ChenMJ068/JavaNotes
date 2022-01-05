package c.mj.notes.thread.thread1;

import lombok.extern.slf4j.Slf4j;

/**
 * 统筹-烧水泡茶
 * create class Test7.java @version 1.0.0 by @author ChenMJ @date 2022-01-04 16:02:00
 */
@Slf4j(topic = "C.MJ.NOTES")
public class Test7 {
    public static void main(String[] args) {
        Thread t1 = new Thread(()->{

            try {
                log.debug("洗水壶");
                Thread.sleep(1000);
                log.debug("烧开水");
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"老王");

        Thread t2 = new Thread(()->{

            try {
                log.debug("洗茶壶");
                Thread.sleep(1000);
                log.debug("烧茶杯");
                Thread.sleep(2000);
                log.debug("拿茶叶");
                Thread.sleep(1000);
                t1.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"小王");
        t1.start();
        t2.start();
    }
}