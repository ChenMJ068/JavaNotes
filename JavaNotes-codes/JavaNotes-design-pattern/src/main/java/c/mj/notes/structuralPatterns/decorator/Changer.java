package c.mj.notes.structuralPatterns.decorator;

/**
 * @author ChenMJ
 * @version Changer.class, v 0.1 2020/4/17 14:44  Exp$
 */
public abstract class Changer implements Transform {

    protected Transform transform;

    public Changer(Transform transform) {
        this.transform = transform;
    }

    @Override
    public void move() {
        transform.move();
    }

}
