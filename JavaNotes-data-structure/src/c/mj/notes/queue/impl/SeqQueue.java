package c.mj.notes.queue.impl;

import c.mj.notes.queue.QQueue;

/**
 * 顺序循环队列
 * @author ChenMJ
 * @version SeqQueue.class, v 0.1 2020/4/9 18:01 n-cz Exp$
 */
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
