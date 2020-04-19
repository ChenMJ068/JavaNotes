package c.mj.notes.structuralPatterns.decorator;

/**
 * @author ChenMJ
 * @version ChangeDecorator.class, v 0.1 2020/4/17 15:30  Exp$
 */
public class ChangeDecorator extends Changer{
    public ChangeDecorator(Transform transform) {
        super(transform);
    }

    @Override
    public void move() {
        super.move();
    }

    private void setBorder(Transform decoratedShape){
        System.out.println("我们来自外星球");
    }
}
