package c.mj.notes.thread.thread2;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * create class Test1.java @version 1.0.0 by @author ChenMJ @date 2022-01-21 09:50:00
 */
@Slf4j(topic = "C.MJ.NOTES")
public class Test33 {
    public static void main(String[] args) {
        //普通数组
        demo(
                ()->new int[10],
                (array)->array.length,
                (array,index) -> array[index]++,
                array -> log.debug("array : {}", Arrays.toString(array))
        );

        //原子保护的数组
        demo(
                ()-> new AtomicIntegerArray(10),
                (array)-> array.length(),
                (array,index)->array.getAndIncrement(index),
                array->log.debug("array : {}",array)
        );
    }

    /**
     * Supplier 提供者 无中生有 ()->{结果}
     * Function 函数 一个参数一个结果 (参数)->{结果}，BiFunction(参数1，参数2)->{结果}
     * Consumer 消费者 一个参数没有结果 (参数)->void，BiConsumer(参数1，参数2)->void
     * @param arraySupplier 提供数组，可以是线程不安全数组或者线程安全的数组
     * @param lengthFun 获取数组长度的方法
     * @param putConsumer 自增方法，回传array，index
     * @param printConsumer 打印数组的方法
     * @param <T> 数据类型
     */
    private  static <T> void demo(
            Supplier<T> arraySupplier,
            Function<T,Integer> lengthFun,
            BiConsumer<T,Integer> putConsumer,
            Consumer<T> printConsumer ){

        List<Thread> ts = new ArrayList<>();
        T array = arraySupplier.get();
        int length = lengthFun.apply(array);
        for (int i = 0; i < length; i++) {
            ts.add(new Thread(()->{
                for (int j = 0; j < 10000; j++) {
                    putConsumer.accept(array,j%length);
                }
            }));
        }
        ts.forEach(Thread::start);
        ts.forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        printConsumer.accept(array);
    }
}