package c.mj.notes.thread.thread1;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * create class Test3.java @version 1.0.0 by @author ChenMJ @date 2021-12-30 11:18:00
 */
@Slf4j(topic = "C.MJ.NOTES")
public class Test3 {
    public static void main(String[] args) throws InterruptedException {

        Thread thread = new Thread("t1") {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(1000);
                    //Thread.sleep(2000);
                } catch (InterruptedException e) {
                    log.debug("wake up ...");
                    //System.out.println("wake up ...");
                    e.printStackTrace();
                }
            }
        };

        thread.start();
        log.debug("t1 state:"+thread.getState());
        //System.out.println("t1 state:"+thread.getState());
        Thread.sleep(1000);
        log.debug("interrupt....");
        //System.out.println("interrupt....");
        thread.interrupt();
        log.debug("t1 state:"+thread.getState());
        //System.out.println("t1 state:"+thread.getState());
    }
}
