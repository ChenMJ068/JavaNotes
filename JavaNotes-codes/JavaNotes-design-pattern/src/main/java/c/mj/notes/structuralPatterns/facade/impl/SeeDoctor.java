package c.mj.notes.structuralPatterns.facade.impl;

import c.mj.notes.structuralPatterns.facade.Hospital;

/**
 * @author ChenMJ
 * @version Doctor.class, v 0.1 2020/4/19 15:22  Exp$
 */
public class SeeDoctor implements Hospital {
    @Override
    public void proxy() {
        System.out.println("检查病因");
    }
}
