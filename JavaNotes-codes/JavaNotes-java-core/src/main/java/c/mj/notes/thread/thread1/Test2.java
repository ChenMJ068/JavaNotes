package c.mj.notes.thread.thread1;

/**
 * create class Test2.java @version 1.0.0 by @author ChenMJ @date 2021-12-30 11:13:00
 */
public class Test2 {
    public static void main(String[] args) throws InterruptedException {

        Thread thread = new Thread("t2") {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        thread.start();
        System.out.println("t2 state:"+thread.getState());
        Thread.sleep(500);
        System.out.println("t2 state:"+thread.getState());
    }
}
