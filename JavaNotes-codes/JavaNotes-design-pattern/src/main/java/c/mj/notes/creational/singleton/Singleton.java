package c.mj.notes.creational.singleton;

/**
 * 双重校验锁，线程安全
 * @author ChenMJ
 * @version Singleton.class, v 0.1 2020/4/16 14:01  Exp$
 */
public class Singleton {
    private volatile static Singleton singleton;

    public Singleton() {
    }

    public static Singleton getInstance(){
        if (singleton == null){
            synchronized (Singleton.class){
                if (singleton == null){
                    return new Singleton();
                }
            }
        }
        return singleton;
    }
}
