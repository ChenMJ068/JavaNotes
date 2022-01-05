package c.mj.notes.creational.builder.biz;

/**
 * @author ChenMJ
 * @version MealBuilder.class, v 0.1 2020/4/16 15:19  Exp$
 */
public class MealBuilder {
    public Meal prepareVegMeal() {
        Meal meal = new Meal();
        meal.addItem(new VegBurger());
        meal.addItem(new Coke());
        return meal;
    }

    public Meal prepareNonVegMeal() {
        Meal meal = new Meal();
        meal.addItem(new ChickenBurger());
        meal.addItem(new OrangeJuice());
        return meal;
    }
}
