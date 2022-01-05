package c.mj.note.ioc.bean;

/**
 * create class Orders.java @version 1.0.0 by @author ChenMJ @date 2021-12-24 16:32:00
 */
public class Orders {
    private String id;
    private String name;
    public Orders() {
        System.out.println("next 1 ---创建bean");
    }

    public void setId(String id) {
        this.id = id;
        System.out.println("next 2 ，set 属性的值");
    }

    public void setName(String name) {
        this.name = name;
        System.out.println("next 2 ，set 属性的值");
    }

    public void initMethod(){
        System.out.println("next 3 执行初始化");
    }
    public void destroyMethod(){
        System.out.println("next 5 执行销毁方法");
    }

    @Override
    public String toString() {
        return "Orders{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
