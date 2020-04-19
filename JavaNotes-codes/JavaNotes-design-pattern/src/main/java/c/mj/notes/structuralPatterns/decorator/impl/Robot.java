package c.mj.notes.structuralPatterns.decorator.impl;

import c.mj.notes.structuralPatterns.decorator.Changer;
import c.mj.notes.structuralPatterns.decorator.Transform;

/**
 * @author ChenMJ
 * @version Robot.class, v 0.1 2020/4/17 15:20  Exp$
 */
public class Robot implements Transform {

    @Override
    public void move() {
        System.out.println("我们是变形金刚");
        this.say();
    }

    public void say(){
        System.out.println("我们会说话");
    }
}
