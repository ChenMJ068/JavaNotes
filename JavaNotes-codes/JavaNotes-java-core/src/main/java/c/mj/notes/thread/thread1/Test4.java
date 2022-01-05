package c.mj.notes.thread.thread1;

import lombok.extern.slf4j.Slf4j;

/**
 * create class Test4.java @version 1.0.0 by @author ChenMJ @date 2021-12-30 11:30:00
 */
@Slf4j(topic = "C.MJ.NOTES")
public class Test4 {
    public static void main(String[] args) {
        Runnable task1 = ()->{
            int count = 0;
            for(;;){
                log.debug("----->task1:\"+ count++");
                //System.out.println("----->task1:"+ count++);
            }
        };

        Runnable task2 = ()->{
            int count = 0;
            for(;;){
                Thread.yield();
                log.debug("           ----->task2:"+ count++);
                //System.out.println("           ----->task2:"+ count++);
            }
        };

        Thread t1 = new Thread(task1,"t1");
        Thread t2 = new Thread(task2,"t2");

        t1.start();
        t2.start();
    }
}
