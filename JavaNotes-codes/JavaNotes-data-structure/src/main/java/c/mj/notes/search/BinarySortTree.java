package c.mj.notes.search;

/**
 * 二叉排序树
 * @author ChenMJ
 * @version BinarySortTree.class, v 0.1 2020/4/14 13:08  Exp$
 */
public class BinarySortTree {
    /**
     * 创建结点类
     */
    class Node {
        private int value;
        private Node left;
        private Node right;

        public Node() {
        }

        public Node(int value) {
            this.value = value;
            this.left = null;
            this.right = null;
        }

        public Node(int value, Node l, Node r) {
            this.value = value;
            this.left = l;
            this.right = r;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public Node getLeft() {
            return left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(Node right) {
            this.right = right;
        }
    }

    /**
     * 创建二叉树的根
     */

    private Node root;

    public BinarySortTree() {
        root = null;
    }

    /**
     * 变空
     */
    public void makeEmpty() {
        root = null;
    }

    /**
     * 查看是否为空
     */
    public boolean isEmpty() {
        return root == null;
    }
    /**
     * 查找最小返回值是Node，调用查看结果时需要.value
     */
    public Node findMin(Node t)
    {
        if (t == null) {
            return null;
        } else if (t.left == null) {
            return t;
        } else{
            return (findMin(t.left));
        }
    }

    /**
     * 查找最大
     */
    public Node findMax(Node t)
    {
        if (t == null) {
            return null;
        } else if (t.right == null) {
            return t;
        } else{
            return (findMax(t.right));
        }
    }

    /**
     * 验证是否存在
     */
    public boolean isContains(int x)
    {
        Node current = root;
        if (root == null) {
            return false;
        }
        while (current.value != x) {
            if (x < current.value) {
                current = current.left;
            }
            if (x > current.value) {
                current = current.right;
            }
            if (current == null) {
                return false;
            }
        }
        return true;
    }

    /**
     *  插入
     */
    public Node insert(int x){
        Node current = root;
        if (root == null) {
            root = new Node(x);
            return root;
        }
        while (true) {
            if (x < current.value) {
                if (current.left == null) {
                    return current.left = new Node(x);}
                else {
                    current = current.left;
                }
            }else if (x > current.value) {
                if (current.right == null) {
                    return current.right = new Node(x);
                }else{
                    current = current.right;
                }
            }
        }
    }

    /**
     * 删除
     */
    public Node remove(int x, Node t)
    {
        if (t == null) {
            return null;
        }
        if (x < t.value) {
            t.left = remove(x, t.left);
        } else if (x > t.value) {
            t.right = remove(x, t.right);
        } else if (t.left != null && t.right != null){
            // 左右节点均不空
            // 找到右侧最小值替代
            t.value = findMin(t.right).value;
            t.right = remove(t.value, t.right);
        } else {
            // 左右单空或者左右都空
            if (t.left == null && t.right == null) {
                t = null;
            } else if (t.right != null) {
                t = t.right;
            } else {
                t = t.left;
            }
            return t;
        }
        return t;
    }
}
