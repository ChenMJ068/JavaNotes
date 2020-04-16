package c.mj.notes.creational.factory.example;

/**
 * @author ChenMJ
 * @version FactoryMain.class, v 0.1 2020/4/15 16:04  Exp$
 */
public class FactoryMain {

    public static void main(String[] args) {
        ShapeFactory shapeFactory = new ShapeFactory();

        Shape circleShape = shapeFactory.getShape(ShapeFactory.CIRCLE);
        circleShape.make();

        Shape rectangleShape = shapeFactory.getShape(ShapeFactory.RECTANGLE);
        rectangleShape.make();

        Shape squareShape = shapeFactory.getShape(ShapeFactory.SQUARE);
        squareShape.make();
    }
}
