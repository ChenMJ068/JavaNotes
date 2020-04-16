package c.mj.notes.creational.prototype;

/**
 * @author ChenMJ
 * @version Rectangle.class, v 0.1 2020/4/16 16:43  Exp$
 */
public class Rectangle extends Shape{

    public Rectangle() {
        this.setType("Rectangle");
    }

    @Override
    public void draw() {
        System.out.println("Inside Rectangle::draw() method.");
    }
}
