package c.mj.notes.creational.factory.example.impl;

import c.mj.notes.creational.factory.example.Shape;

/**
 * @author ChenMJ
 * @version Circle.class, v 0.1 2020/4/15 15:56  Exp$
 */
public class Circle implements Shape {
    @Override
    public void make() {
        System.out.println("Circle production::make() method.");
    }
}
