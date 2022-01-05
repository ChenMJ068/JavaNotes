package c.mj.notes.thread.thread2;

import lombok.extern.slf4j.Slf4j;

/**
 * create class Test2.java @version 1.0.0 by @author ChenMJ @date 2022-01-05 15:44:00
 */
@Slf4j(topic = "C.MJ.NOTES")
public class Test8Lock {
    public static void main(String[] args) {
        Number number = new Number();
        Number number2 = new Number();
        new Thread(() -> {
            log.debug("begin a");
            number.a();
        }).start();
        new Thread(() -> {
            log.debug("begin b");
            number2.b();
        }).start();
//        new Thread(() -> {
//            log.debug("begin c");
//            number.c();
//        }).start();
    }
}
@Slf4j(topic = "C.NUMBER")
class Number{
    public static synchronized void a(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.debug("--1--");
    }
    public static synchronized void b(){
        log.debug("--2--");
    }

    public void c(){
        log.debug("--3--");
    }
}