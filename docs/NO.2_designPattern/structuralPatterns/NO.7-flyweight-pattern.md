## 享元模式
享元模式（Flyweight Pattern），第一个想到的应该就是池技术了，String常量池、数据库连接池、缓冲池等等都是享元模式的应用，所以说
享元模式是池技术的重要实现方式。

享元模式主要用于减少创建对象的数量，以减少内存占用和提高性能。这种类型的设计模式属于结构型模式，它提供了减少对象数量从而改善应
用所需的对象结构的方式。

在享元模式中可以共享的相同内容称为内部状态(IntrinsicState)，而那些需要外部环境来设置的不能共享的内容称为外部状态(Extrinsic State)，
由于区分了内部状态和外部状态，因此可以通过设置不同的外部状态使得相同的对象可以具有一些不同的特征，而相同的内部状态是可以共享的。

在享元模式中通常会出现工厂模式，需要创建一个享元工厂来负责维护一个享元池(Flyweight Pool)用于存储具有相同内部状态的享元对象。

在享元模式中共享的是享元对象的内部状态，外部状态需要通过环境来设置。在实际使用中，能够共享的内部状态是有限的，因此享元对象一般
都设计为较小的对象，它所包含的内部状态较少，这种对象也称为细粒度对象。享元模式的目的就是使用共享技术来实现大量细粒度对象的复用。

#### 享元模式的优点
- 享元模式的优点在于它可以极大减少内存中对象的数量，使得相同对象或相似对象在内存中只保存一份。
- 享元模式的外部状态相对独立，而且不会影响其内部状态，从而使得享元对象可以在不同的环境中被共享。

#### 享元模式的缺点
- 享元模式使得系统更加复杂，需要分离出内部状态和外部状态，这使得程序的逻辑复杂化。
- 为了使对象可以共享，享元模式需要将享元对象的状态外部化，而读取外部状态使得运行时间变长。

#### 使用场景：
- 系统有大量相似对象。 
- 需要缓冲池的场景。

#### 注意事项： 
- 注意划分外部状态和内部状态，否则可能会引起线程安全问题。 
- 这些类必须有一个工厂对象加以控制。

### 代码实现
大学时，很多人都有去图书馆借书的经历。借书的流程很简单，如果书架上有这本书直接拿走，到借阅机上借阅就好了，如果没有，可以到图书
管理处去拿一本新书。

- 定义抽象享元类（Book）
```java
public interface Book {
    void borrow();
}
```
- 定义具体享元类（ConcreteBook）
```java
public class ConcreteBook implements Book {
    private String name;

    public ConcreteBook(String name) {
        this.name = name;
    }

    @Override
    public void borrow() {
        System.out.println("图书馆借出一本书，书名:"+this.name);
    }
}
```
- 享元工厂（Library）
```java
public class Library {
    private Map<String,Book> bookPools = new HashMap<>();

    private static Library library = new Library();

    public static Library getInstance(){
        return library;
    }

    public Book libToBorrow(String bookName){
        Book book = null;
        if (bookPools.containsKey(bookName)){
            book = bookPools.get(bookName);
        }else{
            book = new ConcreteBook(bookName);
            bookPools.put(bookName,book);
        }
        return book;
    }
    public int getAllBookSize(){
        return bookPools.size();
    }
}
```
- 模拟学生去借书
```java
public class Students {
    private static List<Book> books = new ArrayList<>();

    private static Library library;

    private static void studentBorrow(String bookName){
        books.add(library.libToBorrow(bookName));
    }

    public static void main(String[] args) {
        library = Library.getInstance();
        studentBorrow("java编程思想");
        studentBorrow("设计模式之禅");
        studentBorrow("算法导论");

        books.forEach(Book::borrow);

        System.out.println("学生一共借出了"+books.size()+"本书！");
        System.out.println("图书馆实际借出了"+ library.getAllBookSize()+"本书！");
    }
}
```
- 执行结果
```
图书馆借出一本书，书名:java编程思想
图书馆借出一本书，书名:设计模式之禅
图书馆借出一本书，书名:算法导论
学生一共借出了3本书！
图书馆实际借出了3本书！
```
### 总结
- 享元模式运用共享技术有效地支持大量细粒度对象的复用。系统只使用少量的对象，而这些对象都很相似，状态变化很小，可以实现对象的多
次复用，它是一种对象结构型模式。
- 享元模式包含四个角色：抽象享元类声明一个接口，通过它可以接受并作用于外部状态；具体享元类实现了抽象享元接口，其实例称为享元对
象；非共享具体享元是不能被共享的抽象享元类的子类；享元工厂类用于创建并管理享元对象，它针对抽象享元类编程，将各种类型的具体享元
对象存储在一个享元池中。
- 享元模式以共享的方式高效地支持大量的细粒度对象，享元对象能做到共享的关键是区分内部状态和外部状态。其中内部状态是存储在享元对
象内部并且不会随环境改变而改变的状态，因此内部状态可以共享；外部状态是随环境改变而改变的、不可以共享的状态。
- 享元模式主要优点在于它可以极大减少内存中对象的数量，使得相同对象或相似对象在内存中只保存一份；其缺点是使得系统更加复杂，并且
需要将享元对象的状态外部化，而读取外部状态使得运行时间变长。
- 享元模式适用情况包括：一个系统有大量相同或者相似的对象，由于这类对象的大量使用，造成内存的大量耗费；对象的大部分状态都可以外
部化，可以将这些外部状态传入对象中；多次重复使用享元对象。

### 享元模式与单例模式的区别
- 享元设计模式是一个类有很多对象，而单例是一个类仅一个对象。
- 享元模式是为了节约内存空间，提升程序性能，而单例模式则主要是出于共享状态的目的。

源码地址：[设计模式源码](https://github.com/Chenide/JavaNotes)