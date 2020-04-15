package c.mj.notes.linear.impl;

import c.mj.notes.linear.DLinkNode;
import c.mj.notes.linear.LList;

/**循环单链表
 * @author ChenMJ
 * @version CirDoublyLinkedList.class, v 0.1 2020/4/2 17:27 n-cz Exp$
 */
public class CirDoublyLinkedList<T> implements LList<T> {

    public DLinkNode<T> head;

    public CirDoublyLinkedList(DLinkNode<T> head) {
        this.head = new DLinkNode<T>();
        this.head.prev = head;
        this.head.next = head;
    }

    @Override
    public boolean isEmpty() {
        return head.next == null;
    }

    @Override
    public int size() {
        int i = 0;
        DLinkNode<T> node = head.next;
        while (node != null){
            i++;
            node = node.next;
        }
        return i;
    }

    @Override
    public T get(int index) {
        if (index >= 0){
            DLinkNode<T> node = head.next;
            for (int i = 0; node != null && i<index; i++) {
                node = node.next;
            }
            if (node != null){
                return node.data;
            }
        }
        return null;
    }

    @Override
    public void set(int index, T t) {
        if (t == null){
            return;
        }
        if (index >= 0){
            DLinkNode<T> node = head.next;
            for (int i = 0; node != null && i<index; i++) {
                node = node.next;
            }
            if (node != null){
                node.data = t;
            }else{
                throw new IndexOutOfBoundsException(index+"");
            }
        }
    }

    /**
     * 将对象t插入在序号为index结点上
     * 时间复杂度为O(n)
     * @param index 结点
     * @param t 对象
     */
    @Override
    public void insert(int index, T t) {
        //不能插入空对象
        if (t == null){
            return;
        }
        //寻找插入位置，当循环停止时，node指向index-1结点上
        DLinkNode<T> node = this.head;
        for (int i = 0;node.next != this.head && i<index;i++){
            node = node.next;
        }
        //插入在node结点上
        DLinkNode<T> linkNode = new DLinkNode<T>(t,node,node.next);
        node.next.prev = linkNode;
        node.next = linkNode;
    }

    /**
     * 在循环双链表最后添加结点
     * 时间复杂度为O(n)
     * @param t 对象
     */
    @Override
    public void add(T t) {
        if (t == null){
            return;
        }
        //使用尾插法，插入在头结点之前
        DLinkNode<T> linkNode = new DLinkNode<T>(t,head.prev,head);
        head.next.prev = linkNode;
        head.next = linkNode;
    }

    /**
     * 删除序号为i的结点
     * @param i
     * @return
     */
    @Override
    public T remove(int i) {
        if (i >= 0){
            DLinkNode<T> node = this.head.next;
            //定位到待删除的结点
            for (int j = 0;node != head&&j<i;j++){
                node = node.next;
            }

            if (node != head){
                //获得原对象
                T old = node.data;
                node.prev.next = node.next;
                //删除序号为i的结点
                node.next.prev = node.prev;
                return old;
            }
        }
        return null;
    }

    @Override
    public void removeAll() {
        this.head.prev = head;
        this.head.next = head;
    }

    @Override
    public T search(T key) {
        //在unit-10-search中实现
        return null;
    }
}
