package c.mj.notes.creational.builder.biz;

import c.mj.notes.creational.builder.impl.ColdDrink;

/**
 * @author ChenMJ
 * @version OrangeJuice.class, v 0.1 2020/4/16 15:13  Exp$
 */
public class OrangeJuice extends ColdDrink {
    @Override
    public String name() {
        return "冰橙汁";
    }

    @Override
    public double price() {
        return 13.99D;
    }
}
