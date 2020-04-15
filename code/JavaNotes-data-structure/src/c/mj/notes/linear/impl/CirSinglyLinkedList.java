package c.mj.notes.linear.impl;

import c.mj.notes.linear.Node;

/**
 * @author ChenMJ
 * @version CirSinglyLinkedList.class, v 0.1 2020/4/4 17:31 n-cz Exp$
 */
public class CirSinglyLinkedList<T> {

    public Node<T> head;

    public CirSinglyLinkedList(){
        this.head = new Node<T>();
        this.head.next = this.head;
    }

    public boolean isEmpty() {
        return head.next == this.head;
    }

    @Override
    public String toString() {
        String string="(";
        Node<T> node = this.head.next;
        while (node != this.head) {
            string += node.data.toString();
            if (node.next != null){
                string += ",";
            }
            node = node.next;
        }

        return string+")";
    }

    //...其他方法参考同SinglyLinkedList.class
}
