package c.mj.notes.thread.thread1;

/**
 * @author ChenMJ
 * @version ThreadDemo.class, v 0.1 2020/7/21 14:48  Exp$
 */
public class ThreadDemo {
    public static void main(String[] args) {
       /* new Thread(() ->{
            System.out.println("My Thread java 8匿名函数");
        }).start();*/

        //String str = Long.toString();
        System.out.println((long) (System.currentTimeMillis() + Math.floor(Math.random() * 100)));
    }
}
