package c.mj.notes.queue.example;

/**
 * 用递归方式实现数字塔
 *
 * @author ChenMJ
 * @version DigitTower.class, v 0.1 2020/4/11 14:25  Exp$
 */
public class DigitTower {
    private static void line(int i, int n) {
        System.out.print(String.format("%3d", i));
        if (i < n) {
            line(i + 1, n);
            System.out.print(String.format("%3d", i));
        }
    }

    public static void main(String[] args) {
        int n = 9;
        for (int i = 1; i <= n; i++) {
            System.out.print(String.format("%" + 3 * (n - i + 1) + "c", ' '));
            line(1, i);
            System.out.println();
        }
    }
}
