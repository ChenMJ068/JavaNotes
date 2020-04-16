package c.mj.notes.creational.factory.example.impl;

import c.mj.notes.creational.factory.example.Shape;

/**
 * @author ChenMJ
 * @version Square.class, v 0.1 2020/4/15 15:55  Exp$
 */
public class Square implements Shape {
    @Override
    public void make() {
        System.out.println("Square production::make() method.");
    }
}
