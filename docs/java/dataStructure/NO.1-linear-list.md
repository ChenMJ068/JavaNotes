# 线性表
目录：
<!-- TOC -->
- [线性表](#线性表)
    - [1.线性表抽象数据类型](#线性表抽象数据类型)
    - [2.线性表的顺序表示和实现](#线性表的顺序表示和实现)
        - [2.1 线性表的顺序存储结构](#线性表的顺序存储结构)
        - [2.2 顺序表](#顺序表)
        - [2.3 顺序表的插入与删除](#顺序表的插入与删除)
        - [2.4 顺序表的浅拷贝与深拷贝](#顺序表的浅拷贝与深拷贝)
    - [3.线性表的链式表示和实现](#线性表的链式表示和实现)
        - [3.1 单链表](#单链表)
            - [3.1.1 单链表的结点](#单链表的结点)
            - [3.1.2 单链表的遍历操作](#单链表的遍历操作)  
            - [3.1.3 单链表的插入操作](#单链表的插入操作)  
            - [3.1.4 单链表的删除操作](#单链表的删除操作)  
            - [3.1.5 带结点的单链表](#带结点的单链表)  
            - [3.1.6 循环单链表](#单链表的结点)  
        - [3.2 双链表](#双链表)    
        - [3.2 循环双链表](#循环双链表)   
<!-- /MarkdownTOC -->

## 1.线性表抽象数据类型
线性表是其组成元素间具有线性关系的一种线性结构，对线性表的基本操作主要有获取元素值、遍历、插入、删除、查找、替代和排序等，插入和删除可以在线性表的任意位置进行。线性表可以采用顺序存储和链式存储结构表示。

线性表的特点：  
1.有且仅有1个开始结点，没有直接前驱，有1个直接后继；  
2.有且仅有1个结束结点，没有直接后继，有1个直接前驱；  
3.其它结点均有1个直接前驱和1个直接后继。  

**线性表(linear list)** 是由 *n(n>=0)* 个类型相同的元素组成的有限序列。 
***(其实这个接口就是List，为什么重写一遍呢而不是直接贴上jdk源码？是我在参加阿里的面试时，阿里的面试官问我：有没有自己去重写jdk的一些源码？重写这些源码去学习这些底层的实现方式)***

创建一个线性表接口LList.java,信息如下：
```java
public interface LList<T> {
    /**空判断方法*/
    boolean isEmpty();
    /**线性表长度*/
    int size();
    /**返回第i个元素*/
    T get(int index);
    /**设置第i个元素为t*/
    void set(int index,T t);
    /**插入t作为第i个元素*/
    void insert(int index,T t);
    /**在线性表最后插入t*/
    void add(T t);
    /**删除第i个元素*/
    T remove(int i);
    /**删除全部元素*/
    void removeAll();
    /**查找*/
    T search(T key);
}
```
由于线性表可以采用顺序存储和链式存储结构表示，在创建两个实现类：
```java
//顺序存储
public class SequenceList<T> implements LList<T> 
```
```java
//链式存储结构
public class SinglyLinkedList<T> implements LList<T> 
```

## 2.线性表的顺序表示和实现
### 2.1 线性表的顺序存储结构
线性表的顺序存储是用一组连续的内存单元依次存放线性表的数据元素，元素在内存的物理存储次序与它们在线性表中的逻辑相同，这种方式被称为**顺序表(sequence list)**。

线性表的数据元素a<sub>i</sub>的存储地址是它在线性表中位置*i*的线性函数。如图所示：

![线性表的顺序存储结构](../../../docs/img/java/dataStructure/线性表存储结构.png)

顺序表通常采用数组存储数据元素。将线性表的数据元素顺序存放在数组中，数组元素在数组总的物理顺序与线性表中元素的顺序关系相同。
数组是顺序存储随机存取结构，占用一组连续的存储单元，通过下标(序号)识别元素，元素地址是下标的线性函数。一个下标能够唯一确定一个元素，存取任何一个元素所花费的时间是***O(1)***。

###2.2顺序表
类SequenceList<T>实现了接口LList<T>
```java
public class SequentialList<T> implements LList<T> {
    
    private Object[] element;
    private int len;
    
    public SequentialList(int size){
        //如果size<0,会抛出负数组长度异常
        this.element = new Object[size];
        this.len = 0;
    }

    /**
     * 创建容器的默认长度
     */
    public SequentialList() {
        this(64);
    }

    //时间复杂度为O(1)
    @Override
    public boolean isEmpty() {
        return this.len == 0;
    }

    //时间复杂度为O(1)
    @Override
    public int size() {
        return this.len;
    }
    
    //时间复杂度为O(1)
    @Override
    public T get(int index) {
        if (index >= 0 && index < this.len){
            return (T) this.element[index];
        }
        return null;
    }

    //时间复杂度为O(1)
    @Override
    public void set(int index, T t) {
        if (t == null){
            return;
        }
        if (index >= 0 && index < this.len){
            this.element[index] = t;
        }else {
            throw new IndexOutOfBoundsException(index+"");
        }
    }
    
}

```

###2.3 顺序表的插入与删除
顺序表的插入和删除操作要移动数据元素。

插入元素时如果数组已满，则不能插入，会报数据溢出异常(IndexOutOfBoundsException)。解决数据溢出的办法是，将这个数组扩容。
而jdk实现的方式是申请一个更大的数组并复制全部数组元素，这样就扩充了顺序表的容量。
```java
public class SequentialList<T> implements LList<T> {
    //时间复杂度为O(n)
    public void insert(int index, T t) {
        if (t == null){
            return;
        }
        //如果数组满了，则扩容顺序表的容量
        if (this.len == element.length){
            Object[] tmp = this.element;
            this.element = new Object[tmp.length*2];
            for (int i = 0;i<tmp.length;i++){
                this.element[i] = tmp[i];
            }
        }

        //下标容错
        if (index < 0){
            index = 0;
        }
        if (index > this.len){
            index = this.len;
        }

        //元素后移，平均移动len/2
        for (int i = this.len-1;i>=index;i--){
            this.element[i+1] = this.element[i];
        }

        this.element[index] = t;
        this.len++;
    }

    public void add(T t) {
        insert(this.len,t);
    }
}
```
```java
public class SequentialList<T> implements LList<T> {

    public T remove(int i) {
        if (this.len == 0 || i<0 || i >= this.len){
            return null;
        }

        T old = (T) this.element[i];
        for (int j = i;j<this.len-1;j++){
            this.element[j] = this.element[j+1];
        }
        this.element[this.len-1] = null;
        this.len--;
        return old;
    }

    public void removeAll() {
        this.len = 0;
    }
}
```

###2.5 顺序表的浅拷贝与深拷贝
一个类的构造方法，如果其参数是该对象，则称为拷贝构造方法。如：
```
public Student(Student student){ …… }
```
拷贝构造方法的功能是复制对象，以形式参数的实例值初始化当前新创建对象。
- [顺序表的浅拷贝](#顺序表的浅拷贝)：如果一个类将拷贝构造方法实现为逐域拷贝，即将当前对象的各种成员变量值赋值为实际参数对应各成员变量值，称为浅拷贝。  
如SequentialList类的浅拷贝构造方法：

```java
public class SequentialList<T> implements LList<T> {
    /**
     * 浅拷贝的构造方法
     */
    public SequentialList(SequentialList<T> list){
        this.element = list.element;
        this.len = list.len;
    }
}
```
在Java中数据类型是基本类型时，浅拷贝能够实现对象复制功能，数组和类是引用类型，两个数组/对象之间的赋值是引用赋值，数组赋值过程中没有申请新的存储空间，
对象赋值过程中没有创建新的实例。因此当成员变量的数据类型是引用类型时，浅拷贝只复制了对象引用，并没有真正实现对象复制功能。

- [顺序表的深拷贝](#顺序表的深拷贝)：一个类包含引用类型的成员变量时，该类声明的拷贝构造函数，不仅复制对象的所有基本类型成员变量值，
还重新申请引用类型变量占用的动态存储空间，并复制其中所有对象。
如SequentialList类的深拷贝构造方法：
```java
public class SequentialList<T> implements LList<T> {
    /**
     * 深拷贝的构造方法
     */
    public SequentialList(SequentialList<T> list){
            this.len = list.len;
            this.element = new Object[list.element.length];
            for (int i = 0;i<list.element.length;i++){
                this.element[i] = list.element[i];
            }
    }
}
```

### 结合顺序表的特点思考一下约瑟夫环的问题
有n个人，环成一圈开始报数，从s数起，数到m就枪毙一个，然后继续从s数起，数到d就枪毙一个，直到所有枪毙。输出所有人的死亡顺序。

```java
public class Josephus {

    /**
     * @param number 总数量
     * @param start 开始位置
     * @param distance 执行位置
     */
    public Josephus(int number,int start,int distance){
        SequentialList<String> list = new SequentialList<>(number);
        for (int i= 0;i<number;i++){
            list.add((char)('A'+i)+"");
        }
        System.out.println("约瑟夫环("+number+","+start+","+distance+")");
        System.out.println(list.toString());

        int i = start;
        while (list.size() > 1){
            i = (i + distance-1)%list.size();
            System.out.println("删除"+list.remove(i).toString());
            System.out.println(list.toString());
        }
        System.out.println("被赦免者是"+list.get(0).toString());
    }

    public static void main(String[] args) {
        new Josephus(5,0,2);

    }
}
```

##3.线性表的链式表示和实现
线性表的链式存储是用若干地址分散的存储单元存储数据元素，逻辑上相邻的数据元素在物理位置不一定相邻。
存储一个数据元素的存储单元至少包含两个部分——数据域和地址域，数据域中存储的是数据元素值，地址域存储的是后继元素的地址。
###3.1 单链表
单链表是由一个个结点链接而成，如图：
![单链表结构图](../../../docs/img/java/dataStructure/单链表结构图.png)
#### 3.1.1 单链表的结点
单链表是由一个个结点链接而成，不同的链表之间的区别在与结点的不同。  
声明单链表的类Node，代码如下：
```java
public class Node<T> {
    /**数据域，保存数据元素*/
    public T data;
    /**地址域，引用后继结点,通过next链将两个结点链接起来*/
    public Node<T> next;

    public Node(T data, Node<T> next) {
        this.data = data;
        this.next = next;
    }

    public Node() {
        this(null,null);
    }
}
```
``
成员变量next的数据类型是Node类自己，这个类型称为“自引用的类”。是指一个类声明包含一个引用当前类的对象的成员变量。
``
#### 3.1.2 单链表的遍历操作
遍历单链表是指从第一个结点开始，沿着结点的next链，依次访问单链表中的每个结点，并且每个结点只访问一次。
```java
Node<T> node = head;
while(node != null){
    node = node.next;
}
```
语句node = node.next是node移动到后继结点，此时结点间的链接关系没有改变。如果写为“node.next = node”，就变成node.next指向了自己，
改变了链接关系，并且丢失了后继结点，遍历就变成死循环

#### 3.1.3 单链表的插入操作
对于单链表进行插入操作，只要改变结点间的链接关系，不需要移动数据元素。在单链表中插入一个结点，根据位置的不同分为：空表插入、头插入、中间插入、尾插入  
1.空表插入\头插入
```java
 head = new Node<T>(t,head);
```
空表插入也是头插入,当然也可以拆开来写：

```java
if(head == null){
    //空表插入
    head = new Node<T>(t,null);
}else{
    //头插入
    Node<T> node = new Node<T>(t,null);
    node.next = head;
}
```
2.中间插入\尾插入
假设node指向了非空单链表中的某个结点，在node之后的插入q结点

```java
    Node<T> node = new Node<T>(t,null);
    q.next=node.next; //q的后继结点是node的原后继结点
    node.next = q;//q作为node的新后继节点
```
若node指向最后一个结点，node.next == null,可以执行上述代码块。上面代码也可以精简为一句话：
```java
p.next = new Node<T>(t,p.next);
```
#### 3.1.4 单链表的删除操作
在单链表中删除指定结点，只要改变结点的next域就可以改变结点间的链接关系，不需要移动元素。根据结点的位置分为头删除、中间删除、尾删除
1.头删除
删除单链表第一个结点，只要使head指向其后继结点即可
```java
head = head.next;
```
若单链表只有一个结点，则删除该结点后单链表为空表。
#### 3.1.5 带结点的单链表
带头结点的单链表是在单链表的第一个结点之前增加一个特殊结点，称为头结点。头结点的作用是使所有链表（包括空表）的头指针非空，
并使对单链表的插入、删除操作不需要区分为空表或是否在第一个位置进行，从而与其他位置的插入、删除操作一致。
```java
public class SinglyLinkedList<T> implements LList<T> {

    //头指针，指向单链表的头结点
    public Node<T> head;

    /**
     * 重写默认无参构造，构造一个单链表，并带有头结点，data和next都为null
     */
    public SinglyLinkedList() {
        head = new Node<T>();
    }

    /**
     * 浅拷贝构造函数
     * @param list
     */
    /*public SinglyLinkedList(SinglyLinkedList<T> list) {
        head = list.head;
    }*/

    /**
     * 深拷贝构造函数
     * @param list
     */
    public SinglyLinkedList(SinglyLinkedList<T> list) {
        this();
        Node<T> node = list.head.next;
        Node<T> rear = this.head;

        while (node != null){
            rear.next = new Node<T>(node.data,null);
            rear = rear.next;
            node = node.next;
        }
    }

    /**
     * 由指定数组中的多个对象构造单链表，采用尾插入构造单链表
     * @param element 数据元素
     */
    public SinglyLinkedList(T[] element) {
        this();
        Node<T> rear = head;
        for (int i = 0; i < element.length; i++) {
            rear.next = new Node<T>(element[i],null);
            rear = rear.next;
        }
    }

    @Override
    public boolean isEmpty() {
        return head.next == null;
    }

    @Override
    public int size() {
        int i = 0;
        Node<T> node = head.next;
        while (node != null){
            i++;
            node = node.next;
        }
        return i;
    }

    @Override
    public T get(int index) {
        if (index >= 0){
            Node<T> node = head.next;
            for (int i = 0; node != null && i<index; i++) {
                node = node.next;
            }
            if (node != null){
                return node.data;
            }
        }
        return null;
    }

    @Override
    public void set(int index, T t) {
        if (t == null){
            return;
        }
        if (index >= 0){
            Node<T> node = head.next;
            for (int i = 0; node != null && i<index; i++) {
                node = node.next;
            }
            if (node != null){
                node.data = t;
            }else{
                throw new IndexOutOfBoundsException(index+"");
            }
        }
    }

    @Override
    public void insert(int index, T t) {
        if (t == null){
            return;
        }
        Node<T> node = head;
        for (int i=0;node.next!=null&&index<i;i++){
            node = node.next;
        }
        node.next = new Node<T>(t,node.next);
    }

    @Override
    public void add(T t) {
        insert(Integer.MAX_VALUE,t);
    }

    @Override
    public T remove(int i) {
        if (i >= 0){
            Node<T> node = head;
            for (int j = 0; node.next != null && j<i ; j++) {
                node = node.next;
            }
            if (node.next != null){
                T old = node.data;
                node.next = node.next.next;
                return old;
            }
        }
        return null;
    }

    @Override
    public void removeAll() {
        head.next = null;
    }

    @Override
    public T search(T key) {
        ////在unit-10-search中实现
        return null;
    }

    @Override
    public String toString() {
        String string="(";
        Node<T> node = this.head.next;
        while (node != null) {
            string += node.data.toString();
            if (node.next != null){
                string += ",";
            }
            node = node.next;
        }

        return string+")";
    }
}
```
#### 3.1.6 循环单链表
 循环单链表就是链表最后的一个结点的next链保存单链表的头指针head值，形成一个环形结构。
![循环单链表结构图](../../../docs/img/java/dataStructure/循环单链表.png)
 构造方法为：
```java
public class CirSinglyLinkedList<T> {
    
    public Node<T> head;
    
    public CirSinglyLinkedList(){
        this.head = new Node<T>();
        this.head.next = this.head;
    }

    public boolean isEmpty() {
        return head.next == this.head;
    }

    @Override
    public String toString() {
        String string="(";
        Node<T> node = this.head.next;
        while (node != this.head) {
            string += node.data.toString();
            if (node.next != null){
                string += ",";
            }
            node = node.next;
        }

        return string+")";
    }
    
    //...其他方法参考同SinglyLinkedList.class
}

```
###3.2双链表
双链表的每个结点有两个地址域，分别指向它的前驱结点和后继节点，结构如下：
 ![双链表结构图](../../../docs/img/java/dataStructure/双链表结构图.png)
 创建双链表基类：
```java
public class DLinkNode<T> {
    public T data;
    public DLinkNode<T> prev,next;

    public DLinkNode(T data, DLinkNode<T> prev, DLinkNode<T> next) {
        this.data = data;
        this.prev = prev;
        this.next = next;
    }

    public DLinkNode() {
        this(null,null,null);
    }
}
```
双链表的插入和删除操作与单链表不同，其他均与单链表相似
1.双链表的插入
在双链表中插入一个结点，既可插入指定结点之前，也可以插入在指定结点之后。
```java
//插入指定结点之前，假定在结点node之前插入值为x的q结点
q = new DLinkNode<T>(x,node.prev,node);
node.prev.next = q;
node.prev = q;

```
```java
//插入指定结点之后，假定在结点node之后插入值为x的q结点
q = new DLinkNode<T>(x,node,node.next);
if(node.next != null){
    //中间插入时执行
    node.prev.next = q;
}
node.prev = q;
```
2.双链表的删除
代码如下：
```java
node.prev.next = node.next;
if(node.next != null){
    node.next.prev = node.prev;
}
```
### 3.3循环双链表
循环双链表是最后一个结点的next指向头结点，头结点的prev链指向最后一个结点。空双链表是后继结点指向自己的开始结点，开始结点指向自己的后继结点。
![循环双链表结构图](../../../docs/img/java/dataStructure/循环双链表.png)
代码如下：
```java
public class CirDoublyLinkedList<T> implements LList<T> {

    public DLinkNode<T> head;

    public CirDoublyLinkedList(DLinkNode<T> head) {
        this.head = new DLinkNode<T>();
        this.head.prev = head;
        this.head.next = head;
    }

    @Override
    public boolean isEmpty() {
        return head.next == null;
    }

    @Override
    public int size() {
        int i = 0;
        DLinkNode<T> node = head.next;
        while (node != null){
            i++;
            node = node.next;
        }
        return i;
    }

    @Override
    public T get(int index) {
        if (index >= 0){
            DLinkNode<T> node = head.next;
            for (int i = 0; node != null && i<index; i++) {
                node = node.next;
            }
            if (node != null){
                return node.data;
            }
        }
        return null;
    }

    @Override
    public void set(int index, T t) {
        if (t == null){
            return;
        }
        if (index >= 0){
            DLinkNode<T> node = head.next;
            for (int i = 0; node != null && i<index; i++) {
                node = node.next;
            }
            if (node != null){
                node.data = t;
            }else{
                throw new IndexOutOfBoundsException(index+"");
            }
        }
    }

    /**
     * 将对象t插入在序号为index结点上
     * 时间复杂度为O(n)
     * @param index 结点
     * @param t 对象
     */
    @Override
    public void insert(int index, T t) {
        //不能插入空对象
        if (t == null){
            return;
        }
        //寻找插入位置，当循环停止时，node指向index-1结点上
        DLinkNode<T> node = this.head;
        for (int i = 0;node.next != this.head && i<index;i++){
            node = node.next;
        }
        //插入在node结点上
        DLinkNode<T> linkNode = new DLinkNode<T>(t,node,node.next);
        node.next.prev = linkNode;
        node.next = linkNode;
    }

    /**
     * 在循环双链表最后添加结点
     * 时间复杂度为O(n)
     * @param t 对象
     */
    @Override
    public void add(T t) {
        if (t == null){
            return;
        }
        //使用尾插法，插入在头结点之前
        DLinkNode<T> linkNode = new DLinkNode<T>(t,head.prev,head);
        head.next.prev = linkNode;
        head.next = linkNode;
    }

    /**
     * 删除序号为i的结点
     * @param i
     * @return
     */
    @Override
    public T remove(int i) {
        if (i >= 0){
            DLinkNode<T> node = this.head.next;
            //定位到待删除的结点
            for (int j = 0;node != head&&j<i;j++){
                node = node.next;
            }

            if (node != head){
                //获得原对象
                T old = node.data;
                node.prev.next = node.next;
                //删除序号为i的结点
                node.next.prev = node.prev;
                return old;
            }
        }
        return null;
    }

    @Override
    public void removeAll() {
        this.head.prev = head;
        this.head.next = head;
    }

    @Override
    public T search(T key) {
        //在unit-10-search中实现
        return null;
    }
}

```
源码地址：[源码](https://github.com/Chenide/JavaNotes)