## 抽象工厂模式(Abstract Factory Pattern)
在工厂方法模式中具体工厂负责生产具体的产品，每一个具体工厂对应一种具体产品，工厂方法也具有唯一性，一般情况下，
一个具体工厂中只有一个工厂方法或者一组重载的工厂方法。但是有时候我们需要一个工厂可以提供多个产品对象，而不是单一的产品对象

所以抽象工厂模式（Abstract Factory Pattern）是围绕一个超级工厂创建其他工厂。该超级工厂又称为其他工厂的工厂。

在抽象工厂模式中，接口是负责创建一个相关对象的工厂，不需要显式指定它们的类。每个生成的工厂都能按照工厂模式提供对象。

### 抽象工厂模式结构
- AbstractFactory：抽象工厂
- ConcreteFactory：具体工厂
- AbstractProduct：抽象产品
- Product：具体产品
![抽象工厂模式结构类图](../../img/designPattern/creational/abstract/AbatractFactory.jpg)

**时序图**
![时序图](../../img/designPattern/creational/abstract/seq_AbatractFactory.jpg)

- 主要解决：主要解决接口选择的问题。
- 何时使用：系统的产品有多于一个的产品族，而系统只消费其中某一族的产品。
- 如何解决：在一个产品族里面，定义多个产品。
- 关键代码：在一个工厂里聚合多个同类产品

- 优点：当一个产品族中的多个对象被设计成一起工作时，它能保证客户端始终只使用同一个产品族中的对象。
- 缺点：产品族扩展非常困难，要增加一个系列的某一产品，既要在抽象的 Creator 里加代码，又要在具体的里面加代码。

### 实例代码
- 复用工厂模式中创建的Shape.class、Rectangle.class、Square.class、Circle.class   
- 创建一个颜色的接口
```java
public interface Color {
   void fill();
}
```
- 创建具体实现
```java
public class Blue implements Color {
    @Override
    public void fill() {
        System.out.println("Inside Blue::fill() method.");
    }
}
```
```java
public class Green implements Color {
    @Override
    public void fill() {
        System.out.println("Inside Green::fill() method.");
    }
}
```
```java
public class Red implements Color {
    @Override
    public void fill() {
        System.out.println("Inside Red::fill() method.");
    }
}
```
- 为 Color 和 Shape 对象创建抽象类来获取工厂
```java
public abstract class AbstractFactory {
   public abstract Color getColor(String color);
   public abstract Shape getShape(String shape) ;
}
```
- 创建扩展了 AbstractFactory 的工厂类，基于给定的信息生成实体类的对象
```java
public class ColorFactory extends AbstractFactory implements Color {

    @Override
    public Color getColor(String color) {
        if(color == null){
            return null;
        }
        if(color.equalsIgnoreCase("RED")){
            return new Red();
        } else if(color.equalsIgnoreCase("GREEN")){
            return new Green();
        } else if(color.equalsIgnoreCase("BLUE")){
            return new Blue();
        }
        return null;
    }

    @Override
    public Shape getShape(String shapeType) {
        return null;
    }

    @Override
    public void fill() {

    }
}
```

修改ShapeFactory extends AbstractFactory
- 创建一个工厂创造器/生成器类，通过传递形状或颜色信息来获取工厂
```java
public class FactoryProducer {
   public static AbstractFactory getFactory(String choice){
      if(choice.equalsIgnoreCase("SHAPE")){
         return new ShapeFactory();
      } else if(choice.equalsIgnoreCase("COLOR")){
         return new ColorFactory();
      }
      return null;
   }
}
```
- 创建执行
```java
public class AbstractFactoryMain  {
    public static void main(String[] args) {

        //获取形状工厂
        AbstractFactory shapeFactory = FactoryProducer.getFactory("SHAPE");

        //获取形状为 Circle 的对象
        Shape shape1 = shapeFactory.getShape("CIRCLE");

        //调用 Circle 的 make 方法
        shape1.make();

        //获取形状为 Rectangle 的对象
        Shape shape2 = shapeFactory.getShape("RECTANGLE");

        //调用 Rectangle 的 make 方法
        shape2.make();

        //获取形状为 Square 的对象
        Shape shape3 = shapeFactory.getShape("SQUARE");

        //调用 Square 的 make 方法
        shape3.make();

        //获取颜色工厂
        AbstractFactory colorFactory = FactoryProducer.getFactory("COLOR");

        //获取颜色为 Red 的对象
        Color color1 = colorFactory.getColor("RED");

        //调用 Red 的 fill 方法
        color1.fill();

        //获取颜色为 Green 的对象
        Color color2 = colorFactory.getColor("Green");

        //调用 Green 的 fill 方法
        color2.fill();

        //获取颜色为 Blue 的对象
        Color color3 = colorFactory.getColor("BLUE");

        //调用 Blue 的 fill 方法
        color3.fill();
    }
}
```
- 执行结果
```
Circle production::make() method.
Rectangle production::make() method.
Square production::make() method.
Inside Red::fill() method.
Inside Green::fill() method.
Inside Blue::fill() method.
```

源码地址：[设计模式源码](https://github.com/Chenide/JavaNotes)