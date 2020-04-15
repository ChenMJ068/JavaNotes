package c.mj.notes.linear;

/**
 * @author ChenMJ
 * @version DLinkNode.class, v 0.1 2020/4/2 17:18 n-cz Exp$
 */
public class DLinkNode<T> {
    public T data;
    public DLinkNode<T> prev,next;

    public DLinkNode(T data, DLinkNode<T> prev, DLinkNode<T> next) {
        this.data = data;
        this.prev = prev;
        this.next = next;
    }

    public DLinkNode() {
        this(null,null,null);
    }
}
