package c.mj.notes.queue;

/**
 * 队列抽象数据类型
 *
 * @author ChenMJ
 * @version QQueue.class, v 0.1 2020/4/9 17:57 n-cz Exp$
 */
public interface QQueue<T> {
    /**
     * 队列空判断
     */
    boolean isEmpty();

    /**
     * 元素t进入队列
     *
     * @param t 元素
     */
    void enqueue(T t);

    /**
     * 出队列
     */
    T dequeue();
}
