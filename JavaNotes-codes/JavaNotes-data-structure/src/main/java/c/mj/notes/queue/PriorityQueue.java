package c.mj.notes.queue;

import c.mj.notes.linear.impl.SinglyLinkedList;

/**
 * 优先队列
 *
 * @author ChenMJ
 * @version PriorityQueue.class, v 0.1 2020/4/9 18:01 n-cz Exp$
 */
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
