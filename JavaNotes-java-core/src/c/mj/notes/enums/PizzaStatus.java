package c.mj.notes.enums;

/**
 * 枚举示例类
 * @author ChenMJ
 * @version PizzaStatus.class, v 0.1 2020/4/14 10:51  Exp$
 */
public enum PizzaStatus {
    //下单
    ORDERED,
    //准备
    READY,
    //配送
    DELIVERED;
}
class Main{
    public static void main(String[] args) {
        System.out.println(PizzaStatus.ORDERED.name());//ORDERED
        System.out.println(PizzaStatus.READY);//ORDERED
        System.out.println(PizzaStatus.ORDERED.name().getClass());//class java.lang.String
        System.out.println(PizzaStatus.DELIVERED.getClass());//class  c.mj.notes.enums.PizzaStatus
    }
}