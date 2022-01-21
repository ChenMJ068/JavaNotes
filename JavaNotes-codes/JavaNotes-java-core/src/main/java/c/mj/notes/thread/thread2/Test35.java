package c.mj.notes.thread.thread2;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * 原子累计器
 * create class Test3.java @version 1.0.0 by @author ChenMJ @date 2022-01-21 10:34:00
 */
@Slf4j(topic = "C.MJ.NOTES")
public class Test35 {
    public static void main(String[] args) {
        demo(
                () -> new AtomicLong(0),
                (adder) -> adder.getAndIncrement()
        );

        demo(
                () -> new LongAdder(),
                longAdder -> longAdder.increment()
        );
    }

    private static<T> void demo(Supplier<T> adderSupplier, Consumer<T> action){
        T adder = adderSupplier.get();
        List<Thread> ts = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            ts.add(new Thread(()->{
                for (int j = 0; j < 500000; j++) {
                    action.accept(adder);
                }
            }));
        }
        long start = System.nanoTime();
        ts.forEach(Thread::start);
        ts.forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        long end = System.nanoTime();
        log.debug(adder + " cost : {}",(end-start)/1000_000);
    }
}