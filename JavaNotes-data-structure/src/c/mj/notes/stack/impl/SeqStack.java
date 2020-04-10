package c.mj.notes.stack.impl;

import c.mj.notes.stack.SStack;

/**
 * 创建顺序栈
 * @author ChenMJ
 * @version SeqStack.class, v 0.1 2020/4/9 16:57 n-cz Exp$
 */
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
