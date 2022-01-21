package c.mj.notes.thread.thread2;

import lombok.extern.slf4j.Slf4j;
import org.openjdk.jol.info.ClassLayout;


/**
 * create class TestBiased.java @version 1.0.0 by @author ChenMJ @date 2022-01-10 15:03:00
 */
@Slf4j(topic = "C.MJ.NOTES")
public class TestBiased {
    public static void main(String[] args) throws InterruptedException {
        Dog dog = new Dog();
        log.debug(ClassLayout.parseInstance(dog).toPrintable());

        Thread.sleep(4000);
        log.debug(ClassLayout.parseInstance(new Dog()).toPrintable());
    }
}
class Dog{
}