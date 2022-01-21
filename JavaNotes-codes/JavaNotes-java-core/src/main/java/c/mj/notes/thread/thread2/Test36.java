package c.mj.notes.thread.thread2;

import lombok.extern.slf4j.Slf4j;
import sun.misc.Unsafe;

/**
 * create class Test4.java @version 1.0.0 by @author ChenMJ @date 2022-01-21 14:57:00
 */
@Slf4j(topic = "C.MJ.NOTES")
public class Test36 {
    public static void main(String[] args) {
        Account.demo(new MyAtomicInteger(10000));
    }
}

class MyAtomicInteger implements Account{
    private volatile int value;
    private static final long valueOffset;
    static final Unsafe UNSAFE;

    static {
        UNSAFE = Unsafe.getUnsafe();
        try {
            valueOffset = UNSAFE.objectFieldOffset(MyAtomicInteger.class.getDeclaredField("value"));
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public int getValue() {
        return value;
    }

    public void decrement(int amount){
        while (true){
            int prev = this.value;
            int next = prev - amount;
            if (UNSAFE.compareAndSwapInt(this, valueOffset, prev, next)) {
                break;
            }

        }
    }

    public MyAtomicInteger(int value) {
        this.value = value;
    }

    @Override
    public Integer getBalance() {
        return getValue();
    }

    @Override
    public void withdraw(Integer amount) {
        decrement(amount);
    }


}