package c.mj.notes.thread.thread1;

import lombok.extern.slf4j.Slf4j;

/**
 * create class Test6.java @version 1.0.0 by @author ChenMJ @date 2022-01-04 09:18:00
 */
@Slf4j(topic = "C.MJ.NOTES")
public class Test6 {
    public static void main(String[] args) {
        Thread t1 = new Thread("t1"){
            @Override
            public void run() {
                log.debug("running");
            }
        };

        Thread t2 = new Thread("t2"){
            @Override
            public void run() {
               while (true){

               }
            }
        };
        t2.start();

        Thread t3 = new Thread("t3"){
            @Override
            public void run() {
                log.debug("running");
            }
        };
        t3.start();

        Thread t4 = new Thread("t4"){
            @Override
            public void run() {
                synchronized (Test6.class){
                    try {
                        Thread.sleep(1000000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                log.debug("running");
            }
        };
        t4.start();

        Thread t5 = new Thread("t5"){
            @Override
            public void run() {
                try {
                    t2.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        t5.start();

        Thread t6 = new Thread("t6"){
            @Override
            public void run() {
                synchronized (Test6.class){
                    try {
                        Thread.sleep(100000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        t6.start();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        log.debug("t1 state {}",t1.getState());
        log.debug("t2 state {}",t2.getState());
        log.debug("t3 state {}",t3.getState());
        log.debug("t4 state {}",t4.getState());
        log.debug("t5 state {}",t5.getState());
        log.debug("t6 state {}",t6.getState());
    }
}