package c.mj.notes.thread.thread1;

/**
 * create class TestJoin.java @version 1.0.0 by @author ChenMJ @date 2021-12-30 16:46:00
 */
public class TestJoin {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(()->{
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread t2 = new Thread(()->{
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t1.start();
        t2.start();
        long start = System.currentTimeMillis();
        System.out.println("join begin.");
        t2.join();
        System.out.println("t2 join end");
        t1.join();
        System.out.println("t1 join end");
        long end = System.currentTimeMillis();
        System.out.println("end - start = "+ (end-start));
    }
}
