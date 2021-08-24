package c.mj.note;


import c.mj.note.util.TreeNode;

/**
 * 给定二叉搜索树的根结点root，返回值位于范围[low,high]之间的所有的结点的值的和
 * 示例：
 *      （10）
 *      /   \
 *    (5)   (15)
 *    / \     \
 *  (3) (7)   (18)
 *  输入：root=[10,5,15,3,7,null,18] low=7,high=15 输出：32
 *  提示：
 *  树中节点数目在范围[1,2*10的4次方]
 *  1<=Node.val<= 10的5次方
 *  1<=low<=high<=10的5次方
 *  所有Node.val互不相同
 */
public class LeetCode938 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode();
        root.setVal(10);

    }

    public int randeSumBST(TreeNode root, int low, int high){
        if (root == null ){
            return 0;
        }

        if (root.getVal() > high){
            return randeSumBST(root.getLeft(),low,high);
        }

        if (root.getVal() < low){
            return randeSumBST(root.getRight(),low,high);
        }
        return root.getVal() + randeSumBST(root.getLeft(),low,high) + randeSumBST(root.getRight(),low,high);
    }


}

