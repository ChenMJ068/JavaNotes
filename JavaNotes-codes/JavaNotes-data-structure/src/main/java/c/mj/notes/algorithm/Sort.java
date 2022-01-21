package c.mj.notes.algorithm;


import java.util.ArrayList;
import java.util.List;

/**
 *
 * create class BubbleSort.java @version 1.0.0 by @author ChenMJ @date 2022-01-06 16:27:00
 */

public class Sort {
    //堆排序
    public static void myHeapSort(int array[]){

    }

    //基数排序
    public static void basicSort(int array[]){
        int max = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] > max){
                max = array[i];
            }
        }
        int tiems = 0;
        while (max >0){
            max = max/10;
            tiems ++;
        }
        List<ArrayList> queen = new ArrayList<>();
        for (int i = 0;i<10;i++){
            ArrayList q = new ArrayList();
            queen.add(q);
        }
        for (int i=0;i<tiems;i++){
            for (int j = 0; j < array.length; j++) {
                int x = (int) (array[j]%Math.pow(10,i+1)/Math.pow(10,i));
                ArrayList q = queen.get(x);
                q.add(array[j]);
            }
            int count = 0;
            for (int z = 0;z<10;z++){
                while (queen.get(z).size()>0){
                    ArrayList<Integer> c = queen.get(z);
                    array[count] = c.get(0);
                    c.remove(0);
                    count++;
                }
            }
        }
    }

    //快速排序
    public static void quickSort(int array[],int low,int high){
        int pivot,p_pos,i,t;
        if(low < high){
            p_pos = low;
            pivot = array[p_pos];
            for (i = low+1;i<=high;i++) {
                if (array[i] > pivot){
                    p_pos++;
                    t = array[p_pos];
                    array[p_pos] = array[i];
                    array[i] = t;
                }
                t=array[low];
                array[p_pos] = array[i];
                array[i] = t;
            }
            t = array[low];
            array[low] = array[p_pos];
            array[p_pos] = t;

            quickSort(array,low,p_pos-1);
            quickSort(array,p_pos+1,high);
        }
    }

    //选择排序
    public static void selectSort(int array[]){
        int temp =0;
        for (int i = 0; i < array.length-1; i++) {
            int index = i;
            for (int j = i+1; j < array.length; j++) {
                if (array[index] > array[j]){
                    index = j;
                }
            }
            if (index != i){
                temp = array[i];
                array[i] = array[index];
                array[index] = temp;
            }
        }
    }

    //希尔排序
    public static void shellSort(int[] array){
        for (int step = array.length/2; step > 0;step /= 2) {
            for (int i = step; i < array.length; i++) {
                int value = array[i];
                int k;
                for (k = i - step; k >=0 && array[k] > value; k-=step) {
                    array[k+step] = array[k];
                }
                array[k+step] = value;
            }
        }
    }

    //插入排序
    public static void insertionSort(int array[]){
        for (int i = 0; i < array.length; i++) {
            for (int j = i; j > 0; j--) {
                if (array[j] < array[j-1]){
                    int temp = array[j-1];
                    array[j-1] = array[j];
                    array[j] = temp;
                }
            }
        }
    }
    //冒泡排序
    public static void bubbleSort(int array[]){
        int t = 0;
        for (int i = 0; i < array.length-1; i++) {
            for (int j = 0; j < array.length-1-i; j++) {
                if (array[j] >array[j+1]){
                    t = array[j];
                    array[j] = array[j+1];
                    array[j+1] = t;
                }
            }
        }
    }
}
