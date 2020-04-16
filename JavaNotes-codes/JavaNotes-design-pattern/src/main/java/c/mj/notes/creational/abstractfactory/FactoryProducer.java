package c.mj.notes.creational.abstractfactory.example;

import c.mj.notes.creational.factory.example.Shape;
import c.mj.notes.creational.factory.example.ShapeFactory;

/**
 * @author ChenMJ
 * @version FactoryProducer.class, v 0.1 2020/4/15 17:38  Exp$
 */
public class FactoryProducer extends AbstractFactory {
    public static AbstractFactory getFactory(String choice){
        if(choice.equalsIgnoreCase("SHAPE")){
            return new ShapeFactory();
        } else if(choice.equalsIgnoreCase("COLOR")){
            return new ColorFactory();
        }
        return null;
    }

    @Override
    public Color getColor(String color) {
        return null;
    }

    @Override
    public Shape getShape(String shapeType) {
        return null;
    }
}
