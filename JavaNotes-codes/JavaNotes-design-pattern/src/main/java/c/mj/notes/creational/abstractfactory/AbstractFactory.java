package c.mj.notes.creational.abstractfactory.example;

import c.mj.notes.creational.factory.example.Shape;

/**
 * @author ChenMJ
 * @version AbstractFactory.class, v 0.1 2020/4/15 17:18  Exp$
 */
public abstract class AbstractFactory {
    public abstract Color getColor(String color);
    public abstract Shape getShape(String shapeType);
}
