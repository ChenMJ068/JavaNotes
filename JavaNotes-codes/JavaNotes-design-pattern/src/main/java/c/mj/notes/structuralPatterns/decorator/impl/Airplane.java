package c.mj.notes.structuralPatterns.decorator.impl;

import c.mj.notes.structuralPatterns.decorator.Changer;
import c.mj.notes.structuralPatterns.decorator.Transform;

/**
 * @author ChenMJ
 * @version Airplane.class, v 0.1 2020/4/17 14:48  Exp$
 */
public class Airplane implements Transform {
    @Override
    public void move() {
        System.out.println("我们也是变形金刚");
        this.fly();
    }

    public void fly() {
        System.out.println("我们会飞");
    }
}
