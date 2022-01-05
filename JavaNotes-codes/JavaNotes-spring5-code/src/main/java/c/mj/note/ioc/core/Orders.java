package c.mj.note.ioc.core;

/**
 * 有参构造注入
 * create class Orders.java @version 1.0.0 by @author ChenMJ @date 2021-12-23 16:02:00
 */
public class Orders {
    private String name;
    private String address;

    public Orders(String name, String address) {
        this.name = name;
        this.address = address;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
