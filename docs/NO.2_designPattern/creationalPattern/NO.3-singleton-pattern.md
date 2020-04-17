## 单例模式(Singleton Pattern)
单例模式（Singleton Pattern）是 Java 中最简单的设计模式之一。这种类型的设计模式属于创建型模式，它提供了一种创建对象的最佳方式。

这种模式涉及到一个单一的类，该类负责创建自己的对象，同时确保只有单个对象被创建。这个类提供了一种访问其唯一的对象的方式，可以直
接访问，不需要实例化该类的对象。

注意：
- 1.单例类只能有一个实例。
- 2.单例类必须自己创建自己的唯一实例。
- 3.单例类必须给所有其他对象提供这一实例。

单例模式在Spring中是最常见的，因为bean就是单例的

### 几种单例的创建方式
- 双重校验锁 (double-checked locking,DCL)
双重校验锁是线程安全的，需要初始化加载后才能使用
```java
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
```
- 静态内部类
需要初始化，线程安全
这种方式能达到双检锁方式一样的功效，但实现更简单。对静态域使用延迟初始化，应使用这种方式而不是双检锁方式。
这种方式只适用于静态域的情况，双检锁方式可在实例域需要延迟初始化时使用。
```java
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
```

- 枚举方式
使用枚举创建单例是线程安全的且需要初始化后才能使用
创建一个类DataSource.class
```java
public class DataSource {
}

```
创建枚举
```java
public enum SingletonByEnum {
    /**枚举实现单例，它天生就是单例的*/
    INSTANCE;
    private DataSource dataSource = null;
    private SingletonByEnum(){
        dataSource = new DataSource();
    }

    public DataSource getDataSource(){
        return dataSource;
    }
}
```
这种实现方式还没有被广泛采用，但这是实现单例模式的最佳方法。它更简洁，自动支持序列化机制，绝对防止多次实例化。
这种方式是 Effective Java 作者 Josh Bloch 提倡的方式，它不仅能避免多线程同步问题，而且还自动支持序列化机制，防止反序列化重新创建新的对象，绝对防止多次实例化。不过，由于 JDK1.5 之后才加入 enum 特性，用这种方式写不免让人感觉生疏，在实际工作中，也很少用。
不能通过 reflection attack 来调用私有构造方法。

源码地址：[设计模式源码](https://github.com/Chenide/JavaNotes)