package c.mj.notes.thread.thread1;

/**
 * create class Test1.java @version 1.0.0 by @author ChenMJ @date 2021-12-30 11:08:00
 */
public class Test1 {
    public static void main(String[] args) {
        Thread thread1 = new Thread("thread1"){

            @Override
            public void run(){
                System.out.println("running");
            }
        };
        System.out.println(thread1.getState());
        thread1.start();
        System.out.println(thread1.getState());
    }
}
