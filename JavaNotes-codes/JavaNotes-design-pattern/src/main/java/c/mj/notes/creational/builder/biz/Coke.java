package c.mj.notes.creational.builder.biz;

import c.mj.notes.creational.builder.impl.ColdDrink;

/**
 * @author ChenMJ
 * @version Coke.class, v 0.1 2020/4/16 15:10  Exp$
 */
public class Coke extends ColdDrink {
    @Override
    public String name() {
        return "百事可乐";
    }

    @Override
    public double price() {
        return 9.00D;
    }
}
