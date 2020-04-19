## 原型模式(Prototype Pattern)
在Spring的bean创建中有Prototype模式，每次请求都会创建一个新的bean实例。

原型模式（Prototype Pattern）是用于创建重复的对象，同时又能保证性能。这种类型的设计模式属于创建型模式，它提供了一种创建对象的
最佳方式

这种模式是实现了一个原型接口，该接口用于创建当前对象的克隆。当直接创建对象的代价比较大时，则采用这种模式。例如，一个对象需要在
一个高代价的数据库操作之后被创建。我们可以缓存该对象，在下一个请求时返回它的克隆，在需要的时候更新数据库，以此来减少数据库调用。

- 使用场景： 
    - 1、资源优化场景。 
    - 2、类初始化需要消化非常多的资源，这个资源包括数据、硬件资源等。 
    - 3、性能和安全要求的场景。 
    - 4、通过 new 产生一个对象需要非常繁琐的数据准备或访问权限，则可以使用原型模式。 
    - 5、一个对象多个修改者的场景。 
    - 6、一个对象需要提供给其他对象访问，而且各个调用者可能都需要修改其值时，可以考虑使用原型模式拷贝多个对象供调用者使用。 
    - 7、在实际项目中，原型模式很少单独出现，一般是和工厂方法模式一起出现，通过 clone 的方法创建一个对象，然后由工厂方法提供给调
    用者。原型模式已经与 Java 融为浑然一体，大家可以随手拿来使用。
- 优点： 
    - 1、性能提高。 
    - 2、逃避构造函数的约束。
- 缺点： 
    - 1、配备克隆方法需要对类的功能进行通盘考虑，这对于全新的类不是很难，但对于已有的类不一定很容易，特别当一个类引用不支持串行化
    的间接对象，或者引用含有循环结构的时候。 
    -2、必须实现 Cloneable 接口。
- 注意事项：与通过对一个类进行实例化来构造新对象不同的是，原型模式是通过拷贝一个现有对象生成新对象的。浅拷贝实现 Cloneable，
重写，深拷贝是通过实现 Serializable 读取二进制流。

### 代码实现
- 创建一个实现了 Cloneable 接口的抽象类
```java
public abstract class Shape implements Cloneable {
    private String id;
    private String type;

    abstract void draw();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    protected Object clone() {
        Object clone = null;
        try {
            clone = super.clone();
        }catch (CloneNotSupportedException e){
            e.printStackTrace();
        }
        return clone;
    }
}
```
- 创建扩展了上面抽象类的实体类。
```java
public class Circle extends Shape {

    public Circle(){
       this.setType("Circle");
    }

    @Override
    public void draw() {
        System.out.println("Inside Circle::draw() method.");
    }
}
```
```java
public class Rectangle extends Shape{

    public Rectangle() {
        this.setType("Rectangle");
    }

    @Override
    public void draw() {
        System.out.println("Inside Rectangle::draw() method.");
    }
}
```
```java
public class Square extends Shape {
    public Square() {
        this.setType("Square");
    }

    @Override
    public void draw() {
        System.out.println("Inside Square::draw() method.");
    }
}
```
- 创建一个类，从数据库获取实体类，并把它们存储在一个 Hashtable 中。
```java
public class ShapeCache {
    private static Hashtable<String,Shape> shapeHashtable = new Hashtable<>();

    public static Shape getShape(String shapeId){
        Shape shape = shapeHashtable.get(shapeId);
        return (Shape) shape.clone();
    }

    public static void loadCache(){
        Circle circle = new Circle();
        circle.setId("1");
        shapeHashtable.put(circle.getId(),circle);

        Square square = new Square();
        square.setId("2");
        shapeHashtable.put(square.getId(),square);

        Rectangle rectangle = new Rectangle();
        rectangle.setId("3");
        shapeHashtable.put(rectangle.getId(),rectangle);
    }
}
```
- 创建执行
```java
public class PrototypeMain {
    public static void main(String[] args) {
        ShapeCache.loadCache();

        Shape clonedShape = (Shape) ShapeCache.getShape("1");
        System.out.println("Shape : " + clonedShape.getType());

        Shape clonedShape2 = (Shape) ShapeCache.getShape("2");
        System.out.println("Shape : " + clonedShape2.getType());

        Shape clonedShape3 = (Shape) ShapeCache.getShape("3");
        System.out.println("Shape : " + clonedShape3.getType());
    }
}
```
- 执行结果
```
Shape : Circle
Shape : Square
Shape : Rectangle
```

源码地址：[设计模式源码](https://github.com/Chenide/JavaNotes)