package leetcode;

import javax.swing.tree.TreeNode;
import java.util.*;


public class Solution {
    public static void main(String[] args) {

        System.out.println(Math.max(2, 3));
    }

    public int lengthOfLongestSubstring(String s) {
        Queue<Character> queue = new LinkedList<>();
        for (int i = 0; i < s.length(); i++) {
            Character tmps = s.charAt(i);
            Character queueString = queue.peek();
            if (queueString != null && tmps.equals(queueString)) {
                queue.poll();
            }
            queue.add(tmps);
        }
        return queue.size();
    }


    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode pre = new ListNode(0);
        ListNode cur = pre;
        int carry = 0;
        while (l1 != null || l2 != null) {
            int x = l1.val == 0 ? 0 : l1.val;
            int y = l2.val == 0 ? 0 : l2.val;

            int sum = x + y + carry;

            carry = sum / 10;
            sum = sum % 10;
            cur.next = new ListNode(sum);
            cur = cur.next;
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }
        if (carry == 1) {
            cur.next = new ListNode(carry);
        }
        return pre.next;
    }


    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}



