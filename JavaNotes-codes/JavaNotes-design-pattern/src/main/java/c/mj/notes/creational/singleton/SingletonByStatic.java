package c.mj.notes.creational.singleton;

/**
 * 静态类的方式
 * 需要初始化，线程安全
 * 这种方式能达到双检锁方式一样的功效，但实现更简单。对静态域使用延迟初始化，应使用这种方式而不是双检锁方式。
 * 这种方式只适用于静态域的情况，双检锁方式可在实例域需要延迟初始化时使用。
 * @author ChenMJ
 * @version SingletonByStatic.class, v 0.1 2020/4/16 14:05  Exp$
 */
public class SingletonByStatic {
    private static class SingletonHolder{
        private static final SingletonByStatic SINGLETONBYSTATIC = new SingletonByStatic();
    }

    public SingletonByStatic() {
    }
    public static final SingletonByStatic getInstance(){
        return SingletonHolder.SINGLETONBYSTATIC;
    }
}
