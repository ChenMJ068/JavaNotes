package c.mj.notes.creational.abstractfactory.impl;

import c.mj.notes.creational.abstractfactory.Color;

/**
 * @author ChenMJ
 * @version Red.class, v 0.1 2020/4/15 17:12  Exp$
 */
public class Red implements Color {
    @Override
    public void fill() {
        System.out.println("Inside Red::fill() method.");
    }
}
