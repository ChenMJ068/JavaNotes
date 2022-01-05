package c.mj.notes.linear;

/**
 * 手写实现单链表
 *
 * @author ChenMJ
 * @version Node.class, v 0.1 2020/4/1 17:48 n-cz Exp$
 */
public class Node<T> {
    /**
     * 数据域，保存数据元素
     */
    public T data;
    /**
     * 地址域，引用后继结点，通过next链将两个结点链接起来
     */
    public Node<T> next;

    public Node(T data, Node<T> next) {
        this.data = data;
        this.next = next;
    }

    public Node() {
        this(null, null);
    }
}