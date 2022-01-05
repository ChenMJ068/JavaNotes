package c.mj.note;

/**
 * 给定一个非负整数c，判断是否存在两个整数a和b，使得a^2 * b^2 = c
 *
 * @author chenMJ
 */
public class LeetCode633 {
    public static void main(String[] args) {
        LeetCode633 leetCode633 = new LeetCode633();
        System.out.println(leetCode633.judgeSquareSum(96));
    }

    /**
     * 双指针方法
     *
     * @param c
     * @return
     */
    public boolean judgeSquareSum(int c) {
        long left = 0;
        long right = (long) Math.sqrt(c);

        while (left <= right) {
            System.out.println("left=" + left + ",right=" + right);
            long sum = left * left + right * right;
            if (sum == c) {
                System.out.println("left=" + left + ",right=" + right);
                return true;
            }
            if (sum < c) {
                left++;
            }
            if (sum > c) {
                right--;
            }
        }

        return false;
    }
}
