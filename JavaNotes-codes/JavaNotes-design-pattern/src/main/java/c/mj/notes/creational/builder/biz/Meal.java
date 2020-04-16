package c.mj.notes.creational.builder.biz;

import c.mj.notes.creational.builder.Item;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ChenMJ
 * @version Meal.class, v 0.1 2020/4/16 15:14  Exp$
 */
public class Meal{
    private List<Item> items = new ArrayList<>();

    public void addItem(Item item){
        items.add(item);
    }

    public double getCost(){
        double cost = 0D;
        for (Item item:items){
            cost += item.price();
        }
        return cost;
    }

    public void showItems(){
        for (Item item:items){
            System.out.println("购买的是："+item.name());
            System.out.println("包装是："+item.packing().pack());
            System.out.println("价格是："+item.price());
        }
    }
}
