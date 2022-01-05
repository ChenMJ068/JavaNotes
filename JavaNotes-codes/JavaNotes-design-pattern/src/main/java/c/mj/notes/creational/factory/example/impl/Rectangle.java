package c.mj.notes.creational.factory.example.impl;

import c.mj.notes.creational.factory.example.Shape;

/**
 * @author ChenMJ
 * @version Rectangle.class, v 0.1 2020/4/15 15:52  Exp$
 */
public class Rectangle implements Shape {
    @Override
    public void make() {
        System.out.println("Rectangle production::make() method.");
    }
}
