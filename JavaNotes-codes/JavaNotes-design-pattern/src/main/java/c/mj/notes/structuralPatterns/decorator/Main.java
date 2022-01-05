package c.mj.notes.structuralPatterns.decorator;

import c.mj.notes.structuralPatterns.decorator.impl.Robot;

/**
 * @author ChenMJ
 * @version Main.class, v 0.1 2020/4/17 15:21  Exp$
 */
public class Main {
    public static void main(String[] args) {
        Changer changer = new ChangeDecorator(new Robot());
        changer.transform.move();

    }
}
