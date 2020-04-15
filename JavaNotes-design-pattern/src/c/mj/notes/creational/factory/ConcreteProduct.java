package c.mj.notes.creational.factory;

/**
 * @author ChenMJ
 * @version ConcreteProduct.class, v 0.1 2020/4/15 15:28  Exp$
 */
public class ConcreteProduct implements Product {
    @Override
    public void use() {
        System.out.println("具体产品");
    }
}
