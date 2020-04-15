package c.mj.notes.creational.abstractfactory.impl;

import c.mj.notes.creational.abstractfactory.Color;

/**
 * @author ChenMJ
 * @version Green.class, v 0.1 2020/4/15 17:17  Exp$
 */
public class Green implements Color {
    @Override
    public void fill() {
        System.out.println("Inside Green::fill() method.");
    }
}
