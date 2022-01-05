package c.mj.notes.queue.impl;

import c.mj.notes.linear.Node;
import c.mj.notes.queue.QQueue;
import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;

/**
 * @author ChenMJ
 * @version LinkedQueue.class, v 0.1 2020/4/9 18:38 n-cz Exp$
 */
public class LinkedQueue<T> implements QQueue<T> {

    private Node<T> front, rear;

    public LinkedQueue() {
        this.front = this.rear = null;
    }

    @Override
    public boolean isEmpty() {
        return this.front == null && this.rear == null;
    }

    @Override
    public void enqueue(T t) {
        if (t == null) {
            return;
        }
        Node<T> node = new Node<T>(t, null);
        if (this.front == null) {
            this.front = node;
        } else {
            this.rear.next = node;
        }
        this.rear = node;
    }

    @Override
    public T dequeue() {
        if (isEmpty()) {
            return null;
        }
        //取得对头元素
        T tmp = this.front.data;
        //删除对头结点
        this.front = this.front.next;
        if (this.front == null) {
            this.rear = null;
        }
        return tmp;
    }
}
