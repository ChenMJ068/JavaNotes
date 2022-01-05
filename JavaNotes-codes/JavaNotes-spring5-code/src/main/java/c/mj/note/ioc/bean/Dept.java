package c.mj.note.ioc.bean;

/**
 * create class Dept.java @version 1.0.0 by @author ChenMJ @date 2021-12-23 16:37:00
 */
public class Dept {
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Dept{" +
                "name='" + name + '\'' +
                '}';
    }
}
