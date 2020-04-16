package c.mj.notes.creational.builder.impl;

import c.mj.notes.creational.builder.Packing;

/**
 * @author ChenMJ
 * @version Wrapper.class, v 0.1 2020/4/16 15:02  Exp$
 */
public class Wrapper implements Packing {
    @Override
    public String pack() {
        return "纸质包装";
    }
}
