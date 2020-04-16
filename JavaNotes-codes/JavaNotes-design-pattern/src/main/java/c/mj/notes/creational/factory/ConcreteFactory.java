package c.mj.notes.creational.factory;

/**
 * @author ChenMJ
 * @version ConcreteFactory.class, v 0.1 2020/4/15 15:30  Exp$
 */
public class ConcreteFactory implements Factory{
    @Override
    public Product factoryMethod() {
        return new ConcreteProduct();
    }
}
