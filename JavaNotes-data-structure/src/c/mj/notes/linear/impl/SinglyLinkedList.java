package c.mj.notes.linear.impl;

import c.mj.notes.linear.LList;
import c.mj.notes.linear.Node;

/**
 * 单链表,带头结点
 * @author ChenMJ
 * @version SinglyLinkedList.class, v 0.1 2020/3/31 11:11 n-cz Exp$
 */
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
