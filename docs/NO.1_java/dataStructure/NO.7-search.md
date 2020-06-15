# 查找
目录：
<!-- TOC -->
- [查找](#查找)
- [1.基于线性表的查找](#基于线性表的查找)
    - [1.1.顺序查找](#顺序查找)
        - [1.1.1 顺序表的顺序查找](#顺序表的顺序查找)
        - [1.1.2 单链表的顺序查找](#单链表的顺序查找)
        - [1.1.3 顺序查找的算法分析](#顺序查找的算法分析)
    - [1.2.基于有序顺序表的折半查找](#基于有序顺序表的折半查找)
        - [1.2.1 折半查找](#折半查找)
        - [1.2.2 折半查找算法分析](#折半查找算法分析)
    - [1.3.基于索引顺序表的分块查找](#基于索引顺序表的分块查找)
        - [1.3.1 索引](#索引)
        - [1.3.2 分块查找](#分块查找)
        - [1.3.3 分块查找算法分析](#分块查找算法分析)
- [2.散列(hash)](#散列(hash))
    - [2.1 散列表](#散列表)
    - [2.2 散列函数](#散列函数)
    - [2.3 冲突处理](#冲突处理)
        - [2.3.1 开放定址法](#开放定址法)
        - [2.3.2 链地址法](#链地址法)
- [3.二叉排序树和平衡二叉树](#二叉排序树和平衡二叉树)
    - [3.1 二叉排序树](#二叉排序树)
    - [3.2 平衡二叉树](#平衡二叉树)
<!-- /MarkdownTOC -->
## 1.基于线性表的查找
### 1.1.顺序查找
顺序查找算法描述为从线性表的一端开始，依次将每个元素的关键字与给定值进行比较，若相等者，则查找成功；否则继续比较，直到比较完所有
元素，仍未有相等者，则查找不成功，给出结果信息。

顺序查找主要适用于数据量较小的线性表，是最基本最简单的一种查找算法。
#### 1.1.1 顺序表的顺序查找
代码如下：
```
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
```
#### 1.1.2 单链表的顺序查找
代码如下：
```
    public T search(T key) {
        if (key == null){
            return null;
        }
        Node<T> node = this.head.next;
        while(node != null){
            if (node.data.equals(key)){
                return node.data;
            }
            node = node.next;
        }
        return null;
    }
    public boolean contain(T key){
        return this.search(key) != null;
    }
```
#### 1.1.3 顺序查找的算法分析
线性表的长度为n，查找给定值的比较次数取决于该元素在线性表中所处的位置，查找成功时第i(0<=i<=n)个元素的比较次数为i+1。查找不成功时，
需要比较n次。综合可知时间复杂度为$O(n)$
### 1.2.基于有序顺序表的折半查找
#### 1.2.1 折半查找
折半查找又称二分查找：每一次查找，将查找的区间从中间分为两部分，取其中一部分再次进行这样的查找。
折半查找是一种高效的查找方法。它可以明显减少比较次数，提高查找效率。
但是，折半查找的先决条件是查找表中的数据元素必须有序。
 
以下是折半查找的实现：
```java
public class BinarySearch {
    public int binarySearch(int[] arr, int findElem) {
            int low = 0;
            int high = arr.length - 1;
            int mid;
            while (low <= high) {
                mid = (low + high) / 2;
                //如果要查找的元素findElem小于中间位置的元素mid，指向数组的较大端的high索引重新指向中间索引mid的左边（mid-1）
                if (findElem < arr[mid]) {
                    high = mid - 1;
                }
                //如果要查找的元素findElem大于中间位置的元素mid，指向数组的较小端的low索引重新指向中间索引mid的右边（mid+1）
                if (findElem > arr[mid]) {
                    low = mid + 1;
                }
                if (arr[mid] == findElem) {
                    return mid;
                }
            }
            return -1;
        }
}
```

#### 1.2.2 折半查找算法分析

### 1.3.基于索引顺序表的分块查找
#### 1.3.1 索引

#### 1.3.2 分块查找

#### 1.3.3 分块查找算法分析

## 2.散列
散列(hash)就是根据元素的关键字就能够知道元素的存储位置，只需要花费$O(1)$时间就能得到查找结果。它是采用按关键字编码地址的存储
和查找技术，即根据元素的关键字确定元素的存储位置。散列技术的两个关键问题是设计散列函数和处理冲突。
### 2.1 散列表
散列表：在数据元素的关键字与该元素的存储位置之间建立一种对应关系，将这种关系称为散列函数，是由散列函数决定元素存储位置的存储结构。
- 散列函数：jdk的散列函数为 public int hashCode()。散列函数实质上是关键字集合到地址集合的映射，但在实际应用中，因为散列表的存储
有限，散列函数是一个压缩映射，从关键字集合到地址集合是多对一的映射，所以不可避免地会发生冲突
- 冲突：假设两个关键字不相等，但两个关键字的hash值相等，但表示不同关键字的多个元素映射到同一个存储位置，这种现象称为冲突。这个两个关键词
被称为同义词。由同义词引起的冲突称为同义词冲突。
### 2.2 散列函数
一个好的散列函数的标准是，使散列地址均匀地分布在散列表中，尽量避免或减少冲突。
设计一个好的散列函数需要考虑下面几点：
- 1.散列地址必须均匀分布在散列表的全部地址空间；
- 2.函数简单，计算散列函数花费是为$O(1)$；
- 3.使关键字的所有成分都起作用，以反映不同关键字的差异；
- 4.数据元素的查找频率。

在实际应用中关键字的类型都不相同，所以不存在一种散列函数对任何关键字集合都是最好的。
常用的几种散列函数：
- 除留余数法
- 平方取中法
-折叠法
### 2.3 冲突处理
虽然一个好的散列函数能使散列地址分布均匀，但只能减少冲突，而不能从根本上避免冲突。因此，散列表必须有一套完善措施，当冲突发生时
能够有效地处理冲突。
#### 2.3.1 开放定址法
当产生冲突时，开放地址法在散列表内寻找另一个位置存储冲突的元素。寻找位置的方法有多种，常见的有线性探查法，二次探查法等。
详情请参考[散列·开放定址法](https://www.cnblogs.com/dhcao/p/10534728.html)
#### 2.3.2 链地址法
链地址法的散列表，将多个同义词冲突的元素存储在一条单链表中，散列表链接多条同义词单链表。一个元素存储在哪一条同义词单链表中，
是由该元素的散列函数决定的。
详细内容请自行百度。推荐阅读：[HashMap怎样解决散列（hash）冲突？](https://blog.csdn.net/weixin_41163113/article/details/84974414)

## 3.二叉排序树和平衡二叉树
二叉树排序树是一种既支持排序，又支持高效查找、插入、删除操作的数据组织方案，它的查找等操作效率可以到达$O(log_2n)$
### 3.1.二叉排序树
二叉排序树具有以下特点：
- 1.每个结点都有一个作为查找依据的关键字，所有结点的关键字互不相同；
- 2.若一个结点的左子树不空，则左子树所有结点的关键字均小于这个结点的关键字；
- 3.若一个结点的右子树不空，则右子树所有结点的关键字均大于这个结点的关键字；
- 4.每个结点的左、右子树也分别为二叉排序树

**查找**

查找关键字为key的结点，算法描述如下：
- 1.从根结点开始，设p指向根结点；
- 2.将p结点的关键字与key比较，若两者相等，则查找成功；若key较小，则在p的左子树中继续查找；若key较大，则在p的右子树中继续查找。
- 3.重复执行上一步，直到查找成功或者p为空，若p为空，表示查找不成功。

**插入**
与次优二叉树相对，二叉排序树是一种动态树表。其特点是：树的结构通常不是一次生成的，而是在查找过程中，当树中不存在关键字等于给
定值的结点时再进行插入。新插入的结点一定是一个新添加的叶子结点，并且是查找不成功时查找路径上访问的最后一个结点的左孩子或右孩
子结点。

算法描述：
- 1.首先执行查找算法，找出被插结点的父亲结点。
- 2.判断被插结点是其父亲结点的左、右儿子。将被插结点作为叶子结点插入。
- 3.若二叉树为空。则首先单独生成根结点。
**注意：新插入的结点总是叶子结点。**

**删除**
在二叉排序树删去一个结点，分三种情况讨论：
- 1.若p结点为叶子结点，即PL(左子树)和PR(右子树)均为空树。由于删去叶子结点不破坏整棵树的结构，则可以直接删除此子结点。
- 2.若p结点只有左子树PL或右子树PR，此时只要令PL或PR直接成为其双亲结点f的左子树（当p是左子树）或右子树（当p是右子树）即可，作此
修改也不破坏二叉排序树的特性。
- 3.若p结点的左子树和右子树均不空。在删去p之后，为保持其它元素之间的相对位置不变，可按中序遍历保持有序进行调整，可以有两种做法：

其一是令p的左子树为f的左/右(依p是f的左子树还是右子树而定)子树，s为p左子树的最右下的结点，而p的右子树为s的右子树；  
其二是令p的直接前驱（或直接后继）替代p，然后再从二叉排序树中删去它的直接前驱（或直接后继）－即让f的左子树(如果有的话)成
为p左子树的最左下结点(如果有的话)，再让f成为p的左右结点的父结点。

代码实现：
```java
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

```
### 3.2.平衡二叉树
平衡二叉树(又称为AVL树)，必须满足以下特点才能称为平衡二叉树：
- 它的左子树和右子树都是平衡二叉树
- 左子树与右子树的高度之差的绝对值不超过1

链接：[文档地址](https://github.com/Chenide/JavaNotes) [源码地址](https://github.com/Chenide/core-structure-aglo)