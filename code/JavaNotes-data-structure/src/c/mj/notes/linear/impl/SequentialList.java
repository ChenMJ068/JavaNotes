package c.mj.notes.linear.impl;

import c.mj.notes.linear.LList;

/**
 * 顺序表
 * @author ChenMJ
 * @version SequenceList.class, v 0.1 2020/3/31 11:10 n-cz Exp$
 */
public class SequentialList<T> implements LList<T> {
    
    private Object[] element;
    private int len;
    
    public SequentialList(int size){
        //如果size<0,会抛出负数组长度异常
        this.element = new Object[size];
        this.len = 0;
    }

    /**
     * 浅拷贝的构造方法
     */
    public SequentialList(SequentialList<T> list){
        this.element = list.element;
        this.len = list.len;
    }

    /**
     * 深拷贝的构造方法
     */
    /*public SequentialList(SequentialList<T> list){
        this.len = list.len;
        this.element = new Object[list.element.length];
        for (int i = 0;i<list.element.length;i++){
            this.element[i] = list.element[i];
        }
    }*/

    /**
     * 创建容器的默认长度
     */
    public SequentialList() {
        this(64);
    }

    //时间复杂度为O(1)
    @Override
    public boolean isEmpty() {
        return this.len == 0;
    }

    //时间复杂度为O(1)
    @Override
    public int size() {
        return this.len;
    }

    //时间复杂度为O(1)
    @Override
    public T get(int index) {
        if (index >= 0 && index < this.len){
            return (T) this.element[index];
        }
        return null;
    }

    //时间复杂度为O(1)
    @Override
    public void set(int index, T t) {
        if (t == null){
            return;
        }
        if (index >= 0 && index < this.len){
            this.element[index] = t;
        }else {
            throw new IndexOutOfBoundsException(index+"");
        }
    }

    @Override
    public void insert(int index, T t) {
        if (t == null){
            return;
        }
        //如果数组满了，则扩容顺序表的容量
        if (this.len == element.length){
            Object[] tmp = this.element;
            this.element = new Object[tmp.length*2];
            for (int i = 0;i<tmp.length;i++){
                this.element[i] = tmp[i];
            }
        }

        //下标容错
        if (index < 0){
            index = 0;
        }
        if (index > this.len){
            index = this.len;
        }

        //元素后移，平均移动len/2
        for (int i = this.len-1;i>=index;i--){
            this.element[i+1] = this.element[i];
        }

        this.element[index] = t;
        this.len++;
    }

    @Override
    public void add(T t) {
        insert(this.len,t);
    }

    @Override
    public T remove(int i) {
        if (this.len == 0 || i<0 || i >= this.len){
            return null;
        }

        T old = (T) this.element[i];
        for (int j = i;j<this.len-1;j++){
            this.element[j] = this.element[j+1];
        }
        this.element[this.len-1] = null;
        this.len--;
        return old;
    }

    @Override
    public void removeAll() {
        this.len = 0;
    }

    @Override
    public T search(T key) {
        int find = this.indexOf(key);
        return find == -1?null: (T) this.element[find];
    }

    /**
     * 顺序表查找 关键字为key，返回首次出现的元素，找不到就返回-1
     * @param key 检索关键字
     * @return 首次出现下标
     */
    public int indexOf(T key){
        if (key != null){
            for (int i = 0; i < this.len; i++) {
                if(this.element[i].equals(key)){
                    return i;
                }
            }
        }
        return -1;
    }

    public boolean contain(T key){
        return this.indexOf(key) >= 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj){
            if (obj instanceof SequentialList){
                SequentialList<T> list = (SequentialList<T>) obj;
                if (this.size() == this.size()){
                    for (int i = 0;i<this.size();i++){
                        if (!this.get(i).equals(list.get(i))){
                            return false;
                        }
                    }
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public String toString(){
        String string="(";
        if (this.len>0){
            string += this.element[0].toString();
        }
        for (int i = 1;i<this.len;i++){
            string += ","+this.element[i].toString();
        }
        return string+")";
    }
}
