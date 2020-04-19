package c.mj.notes.structuralPatterns.bridge;

/**
 * @author ChenMJ
 * @version Shape.class, v 0.1 2020/4/17 14:01  Exp$
 */
public abstract class Shape implements DrawAPI {
    protected DrawAPI drawAPI;

    protected Shape(DrawAPI drawAPI) {
        this.drawAPI = drawAPI;
    }

    public abstract void draw();
}
