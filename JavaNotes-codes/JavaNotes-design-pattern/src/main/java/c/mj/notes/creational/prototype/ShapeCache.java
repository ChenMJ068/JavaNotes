package c.mj.notes.creational.prototype;

import java.util.Hashtable;

/**
 * @author ChenMJ
 * @version ShapeCache.class, v 0.1 2020/4/16 17:07  Exp$
 */
public class ShapeCache {
    /**
    维护一个注册表，并提供一个找出正确实例原型的方法。最后，提供一个获取新实例的方法，用来委托复制实例的方法生成新实例。
     */
    private static Hashtable<String,Shape> shapeHashtable = new Hashtable<>();

    public static Shape getShape(String shapeId){
        Shape shape = shapeHashtable.get(shapeId);
        return (Shape) shape.clone();
    }

    public static void loadCache(){
        Circle circle = new Circle();
        circle.setId("1");
        shapeHashtable.put(circle.getId(),circle);

        Square square = new Square();
        square.setId("2");
        shapeHashtable.put(square.getId(),square);

        Rectangle rectangle = new Rectangle();
        rectangle.setId("3");
        shapeHashtable.put(rectangle.getId(),rectangle);
    }
}
