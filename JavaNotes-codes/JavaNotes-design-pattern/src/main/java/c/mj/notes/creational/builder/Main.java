package c.mj.notes.creational.builder;

import c.mj.notes.creational.builder.biz.Meal;
import c.mj.notes.creational.builder.biz.MealBuilder;

/**
 * @author ChenMJ
 * @version Main.class, v 0.1 2020/4/16 15:21  Exp$
 */
public abstract class Main extends MealBuilder {
    public static void main(String[] args) {
        MealBuilder mealBuilder = new MealBuilder();

        Meal vegMeal = mealBuilder.prepareVegMeal();
        System.out.println("Veg Meal");
        vegMeal.showItems();
        System.out.println("Total Cost: " +vegMeal.getCost());

        Meal nonVegMeal = mealBuilder.prepareNonVegMeal();
        System.out.println("\n\nNon-Veg Meal");
        nonVegMeal.showItems();
        System.out.println("Total Cost: " +nonVegMeal.getCost());
    }
}
