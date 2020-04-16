package c.mj.notes.creational.abstractfactory;


import c.mj.notes.creational.abstractfactory.impl.Blue;
import c.mj.notes.creational.abstractfactory.impl.Green;
import c.mj.notes.creational.abstractfactory.impl.Red;
import c.mj.notes.creational.factory.example.Shape;

/**
 * @author ChenMJ
 * @version ColorFactory.class, v 0.1 2020/4/15 17:37  Exp$
 */
public class ColorFactory extends AbstractFactory implements Color {

    @Override
    public Color getColor(String color) {
        if(color == null){
            return null;
        }
        if(color.equalsIgnoreCase("RED")){
            return new Red();
        } else if(color.equalsIgnoreCase("GREEN")){
            return new Green();
        } else if(color.equalsIgnoreCase("BLUE")){
            return new Blue();
        }
        return null;
    }

    @Override
    public Shape getShape(String shapeType) {
        return null;
    }

    @Override
    public void fill() {

    }
}
