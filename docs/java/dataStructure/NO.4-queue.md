# 队列
目录：
<!-- TOC -->
- [队列](#队列 )
- [1.队列抽象数据结构](#队列抽象数据结构)
- [2.顺序队列](#顺序队列)
    - [2.1 顺序队列](#顺序队列)
    - [2.2 顺序循环队列](#顺序循环队列)
- [3.链式队列](#链式队列)
- [4.优先队列](#优先队列)
    - [4.1 优先队列及其存储结构](#优先队列及其存储结构)
    - [4.2 优先队列代码实现](#优先队列代码实现)
- [5.递归](#递归)
    - [5.1 递归算法实现](#递归算法实现)
- [6.队列的应用](#队列的应用)
<!-- /MarkdownTOC -->

##1.队列抽象数据结构
队列与栈不同是，栈只能在线性表一端进行插入和删除操作，而队列是分别在线性表两端进行插入和删除操作。向队列中插入元素的过程称为入队(enqueue),删除元素的过程称为出队(dequeue)。
允许入队的一端称为队尾(rear),允许出队一端称为对头(front)。
![队列](../../../docs/img/java/dataStructure/queue.png)
根据上面描述特点创建队列的抽象数据类型
```java
public interface QQueue<T> {
    /**
     * 队列空判断
     */
    boolean isEmpty();

    /**
     * 元素t进入队列
     * @param t 元素
     */
    void enqueue(T t);

    /**
     * 出队列
     */
    T dequeue();
}
```
##2.顺序队列
###2.1 顺序队列
顺序队列使用数组存储数据元素，操作描述如下：
1.当队列空时，设置对头，队尾下标front=rear=-1；  
2.当第一个元素入队时，front=rear=0，同时改变两个下标；  
3.进行入队、出队操作，front、rear随之变化；  
顺序队列存在两个缺点：假溢出，一次入队/出队操作需要同时改变两个下标。
顺序队列之所以会产生假溢出现象，是因为顺序队列的存储单元没有重复使用机制。解决的办法是将顺序队列设计成循环结构。
![顺序队列存储数据及假溢出](../../../docs/img/java/dataStructure/顺序队列存储数据及假溢出.png)
###2.2 顺序循环队列
顺序循环队列就是把队列设计成在逻辑上首尾相接的循环结构。顺序循环队列在以下几个方面对顺序队列做出了改动：  
1.对头、队尾元素下标按照循环规律变化；  
2.约定rear含义及队列空条件；  
3.约定队列存满条件
![顺序循环队列](../../../docs/img/java/dataStructure/顺序循环队列.png)
代码实现：
```java
public class SeqQueue<T> implements QQueue<T> {
    /**
     * 创建队列元素存储数组
     */
    private Object[] element;

    /**
     * front 队列头下标
     * rear 队列尾下标
     */
    private int front,rear;

    public SeqQueue(int size) {
        if (size < 64){
            //设置队列最小值
            size = 64;
        }
        this.element = new Object[Math.abs(size)];
        //设置新创建的队列为空队列
        this.front = this.rear = 0;
    }

    public SeqQueue() {
        this(64);
    }

    @Override
    public boolean isEmpty() {
        return this.front == this.rear;
    }

    @Override
    public void enqueue(T t) {
        if ( t == null){
            return;
        }
        //扩容顺序循环队列条件
        if (this.front == (this.rear+1) % this.element.length){
            Object[] tmp = this.element;
            this.element = new Object[tmp.length*2];
            int i = this.front,j=0;
            while (i != this.rear){
                this.element[j] = tmp[j];
                i = (i+1) % tmp.length;
                j++;
            }
            this.front = 0;
            this.rear = j;
        }
        this.element[this.rear] = t;
        this.rear = (this.rear + 1) % this.element.length;
    }

    @Override
    public T dequeue() {
        if (isEmpty()){
            return null;
        }
        T tmp = (T) this.element[this.front];
        this.front = (this.front + 1) % this.element.length;
        return tmp;
    }

    @Override
    public String toString() {
        String str = "(";
        if (!isEmpty()){
            str += this.element[this.front].toString();
            int i = (this.front + 1) % this.element.length;
            while (i != this.rear){
                str +=","+this.element[i].toString();
                i = (i+1) % this.element.length;
            }
        }
        return str + ")";
    }
}
```
##3.链式队列
链式队列是以不带头结点的单链表实现的。指针front和rear分别指向对头和队尾结点，入队操作将结点链在队尾结点之后，并使rear指向新的
队尾结点；出队操作，当队列不空时，取得队列结点值，删除该结点，并使front指向后继结点。
![链式队列](../../../docs/img/java/dataStructure/链式队列.png)
```java
public class LinkedQueue<T> implements QQueue<T> {

    private Node<T> front,rear;

    public LinkedQueue() {
        this.front =  this.rear = null;
    }

    @Override
    public boolean isEmpty() {
        return this.front == null && this.rear == null;
    }

    @Override
    public void enqueue(T t) {
        if (t == null){
            return;
        }
        Node<T> node = new Node<T>(t,null);
        if (this.front == null){
            this.front = node;
        }else{
            this.rear.next = node;
        }
        this.rear = node;
    }

    @Override
    public T dequeue() {
        if (isEmpty()){
            return null;
        }
        T tmp = this.front.data;
        this.front = this.front.next;
        if (this.front == null){
            this.rear = null;
        }
        return tmp;
    }
}
```
##4.优先队列
什么是优先队列，假设一个队列中的每个元素都有一个优先级，每次出队的是具有最高优先级的元素。这样的队列可以被称为优先队列。
###4.1 优先队列及其存储结构
优先队列可以使用顺序存储结构表示，也可以使用排序的顺序表表示或者采用单链表或排序的单链表表示。优先队列插入元素的时间复杂度为
$O(1)$,出队受到队列长度n的影响所以时间复杂度为$O(n)$
###4.2 优先队列代码实现

优先队列示例代码：
```java
public class PriorityQueue<T extends Comparable<T>> implements QQueue<T> {

    /**
     * 使用单链表创建优先队列存储
     */
    private SinglyLinkedList<T> list;

    public PriorityQueue() {
        this.list = new SinglyLinkedList<T>();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public void enqueue(T t) {
        list.add(t);
    }

    @Override
    public T dequeue() {
        return list.remove(0);
    }
}
```
##5.递归
递归是开发中常用的一种算法，也是数学中一种重要的概念定义方式。

递归定义必须满足以下两个条件：
1.边界条件：至少有一条初始化定义是非递归的  
2.递归通式：由已知函数值逐步递推计算出未知函数值。  
###5.1 递归算法实现
用递归方式实现数字塔：
```java
public class DigitTower {
    private static void line(int i,int n){
        System.out.print(String.format("%3d",i));
        if (i<n){
            line(i+1,n);
            System.out.print(String.format("%3d",i));
        }
    }

    public static void main(String[] args) {
        int n = 9;
        for (int i = 1; i <= n; i++) {
            System.out.print(String.format("%" + 3*(n-i+1)+"c",' '));
            line(1,i);
            System.out.println();
        }
    }
}
```
输出结果：
```
                             1
                          1  2  1
                       1  2  3  2  1
                    1  2  3  4  3  2  1
                 1  2  3  4  5  4  3  2  1
              1  2  3  4  5  6  5  4  3  2  1
           1  2  3  4  5  6  7  6  5  4  3  2  1
        1  2  3  4  5  6  7  8  7  6  5  4  3  2  1
     1  2  3  4  5  6  7  8  9  8  7  6  5  4  3  2  1
```
##6.队列的应用
1.求素数环问题：
```java
public class PrimeRing {
    public static void main(String[] args) {
        new PrimeRing(10);
    }
    public PrimeRing(int n){
        //创建一个顺序表存储素数
        SequentialList<Integer> sList = new SequentialList<Integer>(n);
        sList.add(1);

        SeqQueue<Integer> seqQueue = new SeqQueue<Integer>(n);
        for (int i = 2; i <= n; i++) {
            seqQueue.enqueue(i);
        }

        int i = 0;
        while (!seqQueue.isEmpty()){
            int k = seqQueue.dequeue();
            //判断是否为素数
            if (isPrime(sList.get(i)+k)){
                i++;
                sList.add(k);
            }else{
                seqQueue.enqueue(k);
            }
        }
        System.out.println("素数环："+sList.toString());
    }

    private boolean isPrime(int k){
        if (k == 2){
            return true;
        }
        if (k < 2 || k % 2 == 0){
            return false;
        }
        int j = (int) Math.sqrt(k);
        if (j % 2 == 0){
            j--;
        }
        while (j>2 && k%j != 0){
            j-=2;
        }
        return j<2;
    }
}
```
输出结果：
```
素数环：(1,2,3,4,7,10,9,8,5,6)
```
