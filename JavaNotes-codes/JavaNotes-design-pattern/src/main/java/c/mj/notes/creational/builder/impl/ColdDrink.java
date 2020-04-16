package c.mj.notes.creational.builder;

import c.mj.notes.creational.builder.impl.Bottle;

/**
 * @author ChenMJ
 * @version ColdDrink.class, v 0.1 2020/4/16 15:05  Exp$
 */
public abstract class ColdDrink implements Item{
    @Override
    public Packing packing() {
        return new Bottle();
    }

    @Override
    public abstract double price();
}
