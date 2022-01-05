package c.mj.notes.enums;

/**
 * 枚举类型的属性,方法和构造函数
 *
 * @author ChenMJ
 * @version PizzaByUseDefined.class, v 0.1 2020/4/14 11:02  Exp$
 */
public class PizzaByUseDefined {
    private PizzaStatus pizzaStatus;

    public enum PizzaStatus {
        //下单
        ORDERED,
        //准备
        READY,
        //配送
        DELIVERED;
    }

    public boolean isDelivered() {
        return getPizzaStatus() == PizzaStatus.DELIVERED;
    }

    public boolean isDeliverable() {
        return getPizzaStatus() == PizzaStatus.READY;
    }

    public PizzaStatus getPizzaStatus() {
        return pizzaStatus;
    }
}
