package c.mj.notes.base;

import javafx.scene.control.Cell;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Chart01 {
    public static void main(String[] args) {
        int num = (int) (Math.random() * 10000);
        System.out.println(num);

        Random random = new Random();
        System.out.println(random);

        int[] arr1 = new int[4];
        int[] arr2 = {1, 2, 3, 4};
        int[] arr3 = new int[]{4, 2, 3, 1, 7, 5, 1};

        System.out.println(Arrays.toString(arr2));
        Arrays.sort(arr3);
        System.out.println(Arrays.toString(arr3));

        Cell[] cells = new Cell[4];

        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        arrayList.add(123);

        ArrayList<String> arrayList1 = new ArrayList<String>();
        arrayList1.add("abc");

        System.out.println(arrayList.getClass() == arrayList1.getClass());

    }


}
