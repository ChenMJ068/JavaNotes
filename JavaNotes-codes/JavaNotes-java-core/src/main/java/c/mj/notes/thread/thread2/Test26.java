package c.mj.notes.thread.thread2;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.LockSupport;

/**
 * create class Test26.java @version 1.0.0 by @author ChenMJ @date 2022-01-17 13:57:00
 */
@Slf4j(topic = "C.MJ.NOTES")
public class Test26 {

    static Thread t1;
    static Thread t2;
    static Thread t3;
    public static void main(String[] args) {
        ParkUnpark park = new ParkUnpark(5);

        t1 = new Thread(()->{
            park.print("1",t2);
        });
        t2 = new Thread(()->{
            park.print("2",t3);
        });
        t3 = new Thread(()->{
            park.print("3",t1);
        });

        t1.start();
        t2.start();
        t3.start();

        LockSupport.unpark(t1);
    }
}
@Slf4j(topic = "C.MJ.ParkUnpark")
class ParkUnpark{

    private int loopNumber;

    public ParkUnpark(int loopNumber) {
        this.loopNumber = loopNumber;
    }

    public void print(String str, Thread next){
        for (int i = 0; i < loopNumber; i++) {
            LockSupport.park();
            log.debug("{}",str);
            LockSupport.unpark(next);
        }
    }
}