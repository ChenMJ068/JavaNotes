package c.mj.notes.structuralPatterns.bridge.impl;

import c.mj.notes.structuralPatterns.bridge.DrawAPI;

/**
 * @author ChenMJ
 * @version RedCircle.class, v 0.1 2020/4/17 14:00  Exp$
 */
public class RedCircle implements DrawAPI {
    @Override
    public void drawCircle(int radius, int x, int y) {
        System.out.println("Drawing Circle[ color: red, radius: "
                + radius + ", x: " + x + ", " + y + "]");
    }
}
