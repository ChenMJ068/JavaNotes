package c.mj.note.ioc.bean;

/**
 * create class Emp.java @version 1.0.0 by @author ChenMJ @date 2021-12-23 16:38:00
 */
public class Emp {

    private String name;
    private String ender;

    private Dept dept;

    public void setName(String name) {
        this.name = name;
    }

    public void setEnder(String ender) {
        this.ender = ender;
    }

    public void setDept(Dept dept) {
        this.dept = dept;
    }

    public Dept getDept() {
        return dept;
    }

    @Override
    public String toString() {
        return "Emp{" +
                "name='" + name + '\'' +
                ", ender='" + ender + '\'' +
                ", dept=" + dept +
                '}';
    }
}
