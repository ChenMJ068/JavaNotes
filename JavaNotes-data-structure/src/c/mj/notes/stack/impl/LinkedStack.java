package c.mj.notes.stack.impl;

import c.mj.notes.linear.Node;
import c.mj.notes.stack.SStack;

/**
 * 链式栈
 * @author ChenMJ
 * @version LinkedStack.class, v 0.1 2020/4/9 17:09 n-cz Exp$
 */
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
