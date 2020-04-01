package c.mj.notes.linear;

/**
 * 手写实现单链表
 * @author ChenMJ
 * @version Node.class, v 0.1 2020/4/1 17:48 n-cz Exp$
 */
public class Node<T> {
    public T data;
    public Node<T> next;

    public Node(T data, Node<T> next) {
        this.data = data;
        this.next = next;
    }

    public Node() {
        this(null,null);
    }
}


