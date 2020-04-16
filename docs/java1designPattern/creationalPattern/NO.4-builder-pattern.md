## 建造者模式(Builder Pattern)
建造者模式(Builder Pattern)：将一个复杂对象的构建与它的表示分离，使得同样的构建过程可以创建不同的表示。

建造者模式是一步一步创建一个复杂的对象，它允许用户只通过指定复杂对象的类型和内容就可以构建它们，用户不需要知道内部的具体构建细
节。建造者模式属于对象创建型模式。根据中文翻译的不同，建造者模式又可以称为生成器模式。

比如汽车，它包括车轮、方向盘、发送机等各种部件。而对于大多数用户而言，无须知道这些部件的装配细节，也几乎不会使用单独某个部件，
而是使用一辆完整的汽车，可以通过建造者模式对其进行设计与描述，建造者模式可以将部件和其组装过程分开，一步一步创建一个复杂的对象。

### 模式结构
包含如下角色：
- Builder：抽象建造者
- ConcreteBuilder：具体建造者
- Director：指挥者
- Product：产品角色

- **优点：** 1、建造者独立，易扩展。 2、便于控制细节风险。
- **缺点：** 1、产品必须有共同点，范围有限制。 2、如内部变化复杂，会有很多的建造类。
- **使用场景：**  1、需要生成的对象具有复杂的内部结构。 2、需要生成的对象内部属性本身相互依赖。
- **注意事项：** 与工厂模式的区别是：建造者模式更加关注与零件装配的顺序。

### 适用环境
- 需要生成的产品对象有复杂的内部结构，这些产品对象通常包含多个成员属性。
- 需要生成的产品对象的属性相互依赖，需要指定其生成顺序。
- 对象的创建过程独立于创建该对象的类。在建造者模式中引入了指挥者类，将创建过程封装在指挥者类中，而不在建造者类中。
- 隔离复杂对象的创建和使用，并使得相同的创建过程可以创建不同的产品。

### 代码实现
去肯德基，汉堡、可乐、薯条、炸鸡翅等是不变的，而其组合是经常变化的，生成出所谓的"套餐"
![类图](../../img/designPattern/creational/builder/Package%20builder.png)

- 创建一个包装
```java
public interface Packing {
   public String pack();
}
```
- 创建一个订单接口,包含名称，包装，价格
```java
public interface Item {
    public String name();
    public Packing packing();
    public double price();
}
```
- 实现包装接口，根据产品的类型有纸质包装，和杯子包装
```java
public class Wrapper implements Packing {
    @Override
    public String pack() {
        return "纸质包装";
    }
}
```
```java
public class Bottle implements Packing {
    @Override
    public String pack() {
        return "杯子包装";
    }
}
```
- 创建实现item接口的抽象实现类，并创建默认方法
```java
public abstract class Burger extends Wrapper implements Item{
    @Override
    public Packing packing() {
        return new Wrapper();
    }

    @Override
    public abstract double price();
}
```
```java
public abstract class ColdDrink extends Bottle implements Item {
    @Override
    public Packing packing() {
        return new Bottle();
    }

    @Override
    public abstract double price();
}
```
- 创建扩展了 Burger 和 ColdDrink 的实体类
```java
public class ChickenBurger extends Burger {
    @Override
    public String name() {
        return "鸡肉汉堡";
    }

    @Override
    public double price() {
        return 29.99D;
    }
}
```
```java
public class VegBurger extends Burger {
    @Override
    public String name() {
        return "无肉汉堡";
    }

    @Override
    public double price() {
        return 17.99D;
    }
}
```
```java
public class Coke extends ColdDrink {
    @Override
    public String name() {
        return "百事可乐";
    }

    @Override
    public double price() {
        return 9.00D;
    }
}
```
```java
public class OrangeJuice extends ColdDrink {
    @Override
    public String name() {
        return "冰橙汁";
    }

    @Override
    public double price() {
        return 13.99D;
    }
}
```
- 创建一个 Meal 类，带有上面定义的 Item 对象
 ```java
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
```
- 创建一个 MealBuilder 类，实际的 builder 类负责创建 Meal 对象。
```java
public class MealBuilder {
    public Meal prepareVegMeal(){
        Meal meal = new Meal();
        meal.addItem(new VegBurger());
        meal.addItem(new Coke());
        return meal;
    }

    public Meal prepareNonVegMeal (){
        Meal meal = new Meal();
        meal.addItem(new ChickenBurger());
        meal.addItem(new OrangeJuice());
        return meal;
    }
}
```
- 创建main方法
```java
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
```
- 执行结果
```
Non-Veg Meal
购买的是：鸡肉汉堡
包装是：纸质包装
价格是：29.99
购买的是：冰橙汁
包装是：杯子包装
价格是：13.99
Total Cost: 43.98
```

