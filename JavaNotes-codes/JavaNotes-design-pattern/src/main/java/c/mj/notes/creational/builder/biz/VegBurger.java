package c.mj.notes.creational.builder.biz;

import c.mj.notes.creational.builder.impl.Burger;

/**
 * @author ChenMJ
 * @version VegBurger.class, v 0.1 2020/4/16 15:06  Exp$
 */
public class VegBurger extends Burger {
    @Override
    public String name() {
        return "无肉汉堡";
    }

    @Override
    public double price() {
        return 17.99D;
    }
}
