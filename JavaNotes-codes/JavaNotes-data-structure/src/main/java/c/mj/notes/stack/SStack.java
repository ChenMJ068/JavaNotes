package c.mj.notes.stack;

/**
 * 栈抽象数据类型
 *
 * @author ChenMJ
 * @version SStack.class, v 0.1 2020/4/9 16:55 n-cz Exp$
 */
public interface SStack<T> {
    /**
     * 判断是否为空栈
     */
    boolean isEmpty();

    /**
     * 元素t入栈
     */
    void push(T t);

    /**
     * 出栈
     */
    T pop();

    /**
     * 获取栈元素
     */
    T get();
}