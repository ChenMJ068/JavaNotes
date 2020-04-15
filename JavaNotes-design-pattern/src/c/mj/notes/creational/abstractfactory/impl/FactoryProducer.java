package c.mj.notes.creational.abstractfactory.impl;

import c.mj.notes.creational.abstractfactory.AbstractFactory;
import c.mj.notes.creational.abstractfactory.ColorFactory;
import c.mj.notes.creational.factory.example.ShapeFactory;

/**
 * @author ChenMJ
 * @version FactoryProducer.class, v 0.1 2020/4/15 17:38  Exp$
 */
public class FactoryProducer {
    public static AbstractFactory getFactory(String choice){
        if(choice.equalsIgnoreCase("SHAPE")){
            return new ShapeFactory();
        } else if(choice.equalsIgnoreCase("COLOR")){
            return new ColorFactory();
        }
        return null;
    }
}
