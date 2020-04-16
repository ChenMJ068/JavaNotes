package c.mj.notes.creational.builder.impl;

import c.mj.notes.creational.builder.Item;
import c.mj.notes.creational.builder.Packing;

/**
 * @author ChenMJ
 * @version Burger.class, v 0.1 2020/4/16 15:03  Exp$
 */
public abstract class Burger extends Wrapper implements Item{
    @Override
    public Packing packing() {
        return new Wrapper();
    }

    @Override
    public abstract double price();
}
