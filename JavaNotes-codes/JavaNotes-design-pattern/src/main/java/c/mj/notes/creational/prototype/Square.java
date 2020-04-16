package c.mj.notes.creational.prototype;

/**
 * @author ChenMJ
 * @version Square.class, v 0.1 2020/4/16 16:44  Exp$
 */
public class Square extends Shape {
    public Square() {
        this.setType("Square");
    }

    @Override
    public void draw() {
        System.out.println("Inside Square::draw() method.");
    }
}
