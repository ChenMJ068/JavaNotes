package c.mj.notes.creational.builder.impl;

import c.mj.notes.creational.builder.Item;
import c.mj.notes.creational.builder.Packing;
import c.mj.notes.creational.builder.impl.Bottle;

/**
 * @author ChenMJ
 * @version ColdDrink.class, v 0.1 2020/4/16 15:05  Exp$
 */
public abstract class ColdDrink extends Bottle implements Item {
    @Override
    public Packing packing() {
        return new Bottle();
    }

    @Override
    public abstract double price();
}
