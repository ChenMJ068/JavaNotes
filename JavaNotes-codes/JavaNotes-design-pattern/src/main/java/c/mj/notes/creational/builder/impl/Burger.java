package c.mj.notes.creational.builder;

import c.mj.notes.creational.builder.impl.Wrapper;

/**
 * @author ChenMJ
 * @version Burger.class, v 0.1 2020/4/16 15:03  Exp$
 */
public abstract class Burger implements Item{
    @Override
    public Packing packing() {
        return new Wrapper();
    }

    @Override
    public abstract double price();
}
