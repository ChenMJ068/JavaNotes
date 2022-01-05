package c.mj.notes.enums;

import java.util.EnumSet;

/**
 * @author ChenMJ
 * @version PizzaByGather.class, v 0.1 2020/4/14 11:29  Exp$
 */
public class PizzaByGather {

    private static EnumSet<PizzaStatus> undeliveredPizzaStatuses = EnumSet.of(PizzaStatus.ORDERED, PizzaStatus.READY);

    public enum PizzaStatus {
        //下单
        ORDERED,
        //准备
        READY,
        //配送
        DELIVERED;
    }
}
