package c.mj.notes.creational.factory;

/**
 * @author ChenMJ
 * @version Main.class, v 0.1 2020/4/15 15:36  Exp$
 */
public class Main {
    public static void main(String[] args) {
        Factory factory = new ConcreteFactory();
        Product product = new ConcreteProduct();

        product.use();
    }
}
