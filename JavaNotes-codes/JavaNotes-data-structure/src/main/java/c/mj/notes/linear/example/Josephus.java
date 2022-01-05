package c.mj.notes.linear.example;

import c.mj.notes.linear.impl.SequentialList;

/**
 * 关于约瑟夫环的描述网上有很多版本，这是一个很经典的例子
 * <p>
 * 有n个人，环成一圈开始报数，从s数起，数到m就枪毙一个，然后继续从s数起，数到d就枪毙一个，直到所有枪毙。输出所有人的死亡顺序。
 *
 * @author ChenMJ
 * @version Josephus.class, v 0.1 2020/3/31 14:32 n-cz Exp$
 */
public class Josephus {

    /**
     * @param number   总数量
     * @param start    开始位置
     * @param distance 执行位置
     */
    public Josephus(int number, int start, int distance) {
        SequentialList<String> list = new SequentialList<>(number);
        for (int i = 0; i < number; i++) {
            list.add((char) ('A' + i) + "");
        }
        System.out.println("约瑟夫环(" + number + "," + start + "," + distance + ")");
        System.out.println(list.toString());

        int i = start;
        while (list.size() > 1) {
            i = (i + distance - 1) % list.size();
            System.out.println("删除" + list.remove(i).toString());
            System.out.println(list.toString());
        }
        System.out.println("被赦免者是" + list.get(0).toString());
    }

    public static void main(String[] args) {
        new Josephus(5, 0, 2);

    }
}