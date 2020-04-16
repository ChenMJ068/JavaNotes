package c.mj.notes.creational.abstractfactory.impl;

import c.mj.notes.creational.abstractfactory.Color;

/**
 * @author ChenMJ
 * @version Blue.class, v 0.1 2020/4/15 17:13  Exp$
 */
public class Blue implements Color {
    @Override
    public void fill() {
        System.out.println("Inside Blue::fill() method.");
    }
}
