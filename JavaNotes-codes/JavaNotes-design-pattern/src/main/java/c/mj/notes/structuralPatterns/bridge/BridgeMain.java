package c.mj.notes.structuralPatterns.bridge;

import c.mj.notes.structuralPatterns.bridge.impl.Circle;
import c.mj.notes.structuralPatterns.bridge.impl.GreenCircle;
import c.mj.notes.structuralPatterns.bridge.impl.RedCircle;

/**
 * @author ChenMJ
 * @version BridgeMain.class, v 0.1 2020/4/17 14:06  Exp$
 */
public abstract class BridgeMain extends GreenCircle {
    public static void main(String[] args) {
        Shape redCircle = new Circle(100, 100, 10, new RedCircle());
        Shape greenCircle = new Circle(100, 100, 10, new GreenCircle());

        redCircle.draw();
        greenCircle.draw();
    }
}
