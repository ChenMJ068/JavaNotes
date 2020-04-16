package c.mj.notes.creational.builder.impl;

import c.mj.notes.creational.builder.Packing;

/**
 * @author ChenMJ
 * @version Bottle.class, v 0.1 2020/4/16 15:03  Exp$
 */
public class Bottle implements Packing {
    @Override
    public String pack() {
        return "杯子包装";
    }
}
