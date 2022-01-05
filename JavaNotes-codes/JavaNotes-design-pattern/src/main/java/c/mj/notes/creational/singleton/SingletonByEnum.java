package c.mj.notes.creational.singleton;

/**
 * @author ChenMJ
 * @version SingletonByEnum.class, v 0.1 2020/4/16 14:17  Exp$
 */
public enum SingletonByEnum {
    /**
     * 枚举实现单例
     */
    INSTANCE;
    private DataSource dataSource = null;

    private SingletonByEnum() {
        dataSource = new DataSource();
    }

    public DataSource getDataSource() {
        return dataSource;
    }
}

class Main {
    public static void main(String[] args) {
        DataSource dataSource1 = SingletonByEnum.INSTANCE.getDataSource();
        DataSource dataSource2 = SingletonByEnum.INSTANCE.getDataSource();
        System.out.println(dataSource1 == dataSource2);
    }
}