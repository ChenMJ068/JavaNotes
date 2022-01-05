package c.mj.notes.creational.factory.example;

import c.mj.notes.creational.abstractfactory.AbstractFactory;
import c.mj.notes.creational.abstractfactory.Color;
import c.mj.notes.creational.factory.example.impl.Circle;
import c.mj.notes.creational.factory.example.impl.Rectangle;
import c.mj.notes.creational.factory.example.impl.Square;

/**
 * @author ChenMJ
 * @version ShapeFactory.class, v 0.1 2020/4/15 15:58  Exp$
 */
public class ShapeFactory extends AbstractFactory {

    public final static String CIRCLE = "CIRCLE";
    public final static String RECTANGLE = "RECTANGLE";
    public final static String SQUARE = "SQUARE";

    @Override
    public Shape getShape(String shapeType) {
        if (shapeType == null) {
            return null;
        }
        if (shapeType.equalsIgnoreCase(CIRCLE)) {
            return new Circle();
        }
        if (shapeType.equalsIgnoreCase(RECTANGLE)) {
            return new Rectangle();
        }
        if (shapeType.equalsIgnoreCase(SQUARE)) {
            return new Square();
        }
        return null;
    }

    /**
     * 抽象模式中添加的方法
     */
    @Override
    public Color getColor(String color) {
        return null;
    }
}
