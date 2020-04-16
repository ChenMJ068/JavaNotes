package c.mj.notes.creational.prototype;

/**
 * @author ChenMJ
 * @version Circle.class, v 0.1 2020/4/16 16:46  Exp$
 */
public class Circle extends Shape {

    public Circle(){
       this.setType("Circle");
    }

    @Override
    public void draw() {
        System.out.println("Inside Circle::draw() method.");
    }
}
