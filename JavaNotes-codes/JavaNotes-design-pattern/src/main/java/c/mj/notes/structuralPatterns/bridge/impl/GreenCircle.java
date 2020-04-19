package c.mj.notes.structuralPatterns.bridge.impl;

import c.mj.notes.structuralPatterns.bridge.DrawAPI;

/**
 * @author ChenMJ
 * @version GreenCircle.class, v 0.1 2020/4/17 14:01  Exp$
 */
public class GreenCircle implements DrawAPI {
    @Override
    public void drawCircle(int radius, int x, int y) {
        System.out.println("Drawing Circle[ color: green, radius: "
                + radius +", x: " +x+", "+ y +"]");
    }
}
