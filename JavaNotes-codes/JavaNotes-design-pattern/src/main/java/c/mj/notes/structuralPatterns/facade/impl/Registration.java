package c.mj.notes.structuralPatterns.facade.impl;

import c.mj.notes.structuralPatterns.facade.Hospital;

/**
 * @author ChenMJ
 * @version Registration.class, v 0.1 2020/4/19 15:21  Exp$
 */
public class Registration implements Hospital {
    @Override
    public void proxy() {
        System.out.println("先挂号");
    }
}
