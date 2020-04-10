# 栈
目录：
<!-- TOC -->
- [栈](#栈)
- [1.栈抽象数据结构](#栈抽象数据结构)
- [2.顺序栈](#顺序栈)
- [3.链式栈](#链式栈)
- [4.栈的应用](#栈的应用)
    - [4.1 栈嵌套调用机制的实现基础](#栈嵌套调用机制的实现基础)
    - [4.2 使用栈以非递归方式实现递归算法](#使用栈以非递归方式实现递归算法)
<!-- /MarkdownTOC -->

##1.栈抽象数据结构
**栈(stack)**是一种特殊的线性表，插入和删除操作只能在线性表的一端进行，因此栈也被称为后进先出表。允许操作的一端被称为栈顶(top),
不允许操作的一端称为栈底(bottom)。栈中插入元素的操作称为入栈(push)，删除元素的操作称为出栈(pop)，没有元素的栈称为空栈。

![栈](../../../docs/img/java/dataStructure/栈.png)

根据上面描述栈的特点，创建栈抽象数据类型接口SStack.class，代码如下：

```java
public interface SStack<T>{
    boolean isEmpty();
    void push();
    T pop();
    T get();
}
```

##2.顺序栈
采用顺序存储结构的栈被称为顺序栈，顺序栈操作数据如图所示：
![顺序栈](../../../docs/img/java/dataStructure/顺序栈.png)

根据栈的接口，实现顺序栈的代码如下：
```java
public class SeqStack<T> implements SStack<T> {
    /**
     * 创建栈元素存储数组
     */
    private Object[] element;

    /**
     *  栈顶元素的下标
     */
    private int top;

    /**
     * 构造栈容器容量
     * @param size 容量
     */
    public SeqStack(int size) {
        this.element = new Object[Math.abs(size)];
        this.top = -1;
    }

    public SeqStack() {
        this(64);
    }

    @Override
    public boolean isEmpty() {
        return top == -1;
    }

    @Override
    public void push(T t) {
        if (t == null){
            return;
        }
        //如果栈满了，将栈容量扩容两倍
        if (this.top == element.length-1){
            Object[] tmp = this.element;
            //扩容
            this.element = new Object[tmp.length*2];
            for (int i = 0; i < tmp.length; i++) {
                //复制元素
                this.element[i] = tmp[i];
            }
        }
        this.top++;
        this.element[this.top] = t;
    }

    @Override
    public T pop() {
        return this.top==-1?null: (T) this.element[this.top--];
    }

    @Override
    public T get() {
        return this.top == -1?null: (T) this.element[this.top];
    }
}
```
由于栈只能在栈顶位置进行插入、删除操作，所以 push(T t)、pop()、 get()方法的时间复杂度为$O(1)$，当容器需要扩容时push(T t)的时间复杂度为$O(n)$
##3.链式栈
采用链式存储结构的栈称为链式栈。链式栈使用单链表即可，不需要使用循环链表或双链表，并且头结点的作用不明显，因此以下采用不带头结点的单链表实现栈。
![链式栈](../../../docs/img/java/dataStructure/链式栈.png)

```java
public class LinkedStack<T> implements SStack<T> {

    /**
     * 栈顶结点
     */
    private Node<T> top;

    /**
     * 构造一个空栈
     */
    public LinkedStack() {
        this.top = null;
    }

    @Override
    public boolean isEmpty() {
        return this.top == null;
    }

    @Override
    public void push(T t) {
        if (t != null){
            this.top = new Node(t,this.top);
        }
    }

    @Override
    public T pop() {
        if (this.top == null){
            return null;
        }
        T tmp = this.top.data;
        this.top = this.top.next;
        return tmp;
    }

    @Override
    public T get() {
        return this.top == null ?null:this.top.data;
    }
}
```
##4.栈的应用
栈在面试时是必会问道的问题，一般都是在jvm，堆栈等问题中体现。程序开发中在实现嵌套调用、递归调用时、非递归方式实现递归算法或实现
非线性结构的深度遍历算法等软件设计中都会用到栈。
###4.1 栈嵌套调用机制的实现基础
开发中常会在一个方法体中调用另一个方法的方式就是嵌套调用。为什么嵌套调用会用到栈呢？因为在方法调用的次序和方法返回的次序正好相反，
如果借助一个栈“记住”方法从何而来，那么就能获得方法返回的路径。当方法被调用的时，操作系统将该方法有关信息(地址、参数、局部变量等)入栈，
记录当前信息；方法调用完返回时，出栈，获取被记录的方法信息，程序继续执行。也有称这个过程为保护现场、恢复现场。

![方法嵌套调用规则及系统栈](../../../docs/img/java/dataStructure/方法嵌套调用规则及系统栈.png)
###4.2 使用栈以非递归方式实现递归算法
在程序中出现的表达式，其定义是递归的，求值也有递归算法。通过一个表达式（1+2）*3+4来展示jvm是怎么将表达式编译成能够正确求值的指令。

