package c.mj.notes.creational.builder.biz;

import c.mj.notes.creational.builder.impl.Burger;

/**
 * @author ChenMJ
 * @version ChickenBurger.class, v 0.1 2020/4/16 15:09  Exp$
 */
public class ChickenBurger extends Burger {
    @Override
    public String name() {
        return "鸡肉汉堡";
    }

    @Override
    public double price() {
        return 29.99D;
    }
}
