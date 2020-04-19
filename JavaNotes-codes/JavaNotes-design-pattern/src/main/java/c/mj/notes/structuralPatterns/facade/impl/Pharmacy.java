package c.mj.notes.structuralPatterns.facade.impl;

import c.mj.notes.structuralPatterns.facade.Hospital;

/**
 * @author ChenMJ
 * @version Pharmacy.class, v 0.1 2020/4/19 15:23  Exp$
 */
public class Pharmacy implements Hospital {
    @Override
    public void proxy() {
        System.out.println("取药");
    }
}
