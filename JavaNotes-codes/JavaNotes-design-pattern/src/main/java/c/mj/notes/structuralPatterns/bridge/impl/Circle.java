package c.mj.notes.structuralPatterns.bridge.impl;

import c.mj.notes.structuralPatterns.bridge.DrawAPI;
import c.mj.notes.structuralPatterns.bridge.Shape;

/**
 * @author ChenMJ
 * @version Circle.class, v 0.1 2020/4/17 14:04  Exp$
 */
public class Circle extends Shape {

    private int x, y, radius;

    public Circle(int x, int y, int radius, DrawAPI drawAPI) {
        super(drawAPI);
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    @Override
    public void draw() {
        drawAPI.drawCircle(radius,x,y);
    }

    @Override
    public void drawCircle(int radius, int x, int y) {

    }
}
