package c.mj.notes.storting;

import java.util.Arrays;
import java.util.concurrent.atomic.DoubleAccumulator;

/**
 * 插入排序演示
 * @author ChenMJ
 * @version ArraySort.class, v 0.1 2020/4/11 17:34  Exp$
 */
public class ArraySort {

    public static void main(String[] args) {
        int[] array = {67,45,12,98,64,33,54,77,11,48};
        //insertSort(array);
        //binaryInsertSort(array);
        //shellSort(array);
        //bubbleSort(array);
        //quickSort(array);
        //selectSort(array);
       //headSort(array);
        mergeSort(array);
    }

    /**
     * 归并排序
     * @param array 待排序的数组
     */
    public static void mergeSort(int[] array){
        int[] y = new int[array.length];
        int n = 1;
        while (n<array.length){
            mergePass(array,y,n);
            n *= 2;
            if (n < array.length){
                mergePass(y,array,n);
                n *= 2;
            }
        }
        System.out.println("归并排序:"+Arrays.toString(array));
    }

    /**
     * 一趟归并，依次将数组X中相邻子序列两两归并到数组Y中，子序列长度为n。
     * 如果相邻子序列
     * @param X 数组 X
     * @param Y 数组 Y
     * @param n 子序列长度
     */
    private static void mergePass(int[] X,int[] Y,int n){
        int i = 0;
        while (i<X.length-2*n+1){
            merge(X,Y,i,i+n,n);
            i += 2*n;
        }
        if (i+n<X.length){
            merge(X,Y,i,i+n,n);
        }else{
            if (X.length - i >= 0){
                System.arraycopy(X, i, Y, i, X.length - i);
            }
        }
    }

    /**
     * 一次归并方法实现，m、r分别是相邻连个已排序子序列的下标
     * @param X 数组 X
     * @param Y 数组 Y
     * @param m 下标
     * @param r 下标
     * @param n 后一个子序列长度
     */
    private static void merge(int[] X,int[] Y,int m,int r,int n){
        int i=m,j=r,k=m;
        //将x中两个相邻子序列归并Y中
        while (i<r && j<r+n && j<X.length){
            //将较小值复制到Y中
            if (X[i] < X[j]){
                Y[k++] = X[i++];
            }else{
                //将前一个子序列剩余元素复制到y中
                Y[k++] = X[j++];
            }
        }
        while (i<r){
            Y[k++] = X[i++];
        }
        //将后一个子序列剩余元素复制到y中
        while (j<r+n && j<X.length){
            Y[k++] = X[j++];
        }
    }
    /**
     * 堆排序
     * @param array 待排序的数组
     */
    public static void headSort(int[] array){
        int n = array.length;
        for (int j= array.length/2-1;j>=0;j--){
            sift(array,j,n-1);
        }
        for (int j = n-1;j>0;j--){
            int temp = array[0];
            array[0] = array[j];
            array[j] = temp;
            sift(array,0,j-1);
            System.out.println("堆排序当前变更结果:"+ Arrays.toString(array));
        }
    }

    /**
     * 将以begin为根的子树调整为最小堆
     * @param array 待排序的数组
     * @param begin 序列上届
     * @param end 序列下界
     */
    private static void sift(int[] array,int begin,int end){
        //i为子树的根，j为i结点的左孩子
        int i = begin,j=2*i+1;
        //获得第i个元素的值
        int temp = array[i];
        //沿较小孩子结点向下筛选
        while(j<=end){
            if (j<end && array[j]>array[j+1]){
                j++;
            }
            if (temp > array[j]){
                //孩子结点中的较小值上移
                array[i] = array[j];
                i = j;
                j = 2*i+1;
            }else{
                break;
            }
        }
        array[i] = temp;
    }
    /**
     * 直接选择排序
     * @param array 待排序的数组
     */
    public static void selectSort(int[] array){
        for (int i = 0; i < array.length-1; i++) {
            int min = 1;
            for (int j = i+1; j < array.length; j++) {
                if (array[j] < array[min]){
                    min = j;
                }
            }
            if (min != i){
                int temp = array[i];
                array[i] = array[min];
                array[min] = temp;
            }
            System.out.println("第"+i+"次:"+ Arrays.toString(array));
        }
    }

    /**
     * 快速排序
     * @param array 待排序的数组
     */
    public static void quickSort(int[] array){
        Arrays.sort(array);
        System.out.println("快速排序后:"+ Arrays.toString(array));
    }

    /**
     *冒泡排序
     * @param array 待排序的数组
     */
    public static void bubbleSort(int[] array){
        for (int i = 1; i < array.length; i++) {
            for (int j = 0; j < array.length-1; j++) {
                //降序
                if (array[j] < array[j+1]){
                    int temp = array[j+1];
                    array[j+1] = array[j];
                    array[j] = temp;
                }
                //升序
               /* if (array[j] > array[j+1]){
                    int temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;
                }*/
            }
            System.out.println("第"+i+"次:"+ Arrays.toString(array));
        }
    }

    /**
     * 直接插入排序
     * @param array 待排序的数组
     */
    public static void insertSort(int[] array){
        //n-1次扫描，从数组的第二个元素开始
        for (int i = 1; i < array.length; i++) {
            //每次将array[i]插入到前面已排序的数组中
            int temp = array[i],j;
            //将前面较大的元素向后移动
            for (j = i-1;j>=0 && temp < array[j]; j--) {
                array[j+1] = array[j];
            }
            //temp值插入目标位置
            array[j+1] = temp;
            System.out.println("第"+i+"次:"+ Arrays.toString(array));
        }
    }
    /**
     * 折半直接插入排序
     * @param array 待排序的数组
     */
    public static void binaryInsertSort(int[] array){
        for(int i = 1; i < array.length; i++){
            int temp = array[i],low = 0,high = i - 1;
            //折半查找应该插入的位置
            while(low <= high){
                int mid = (low + high) / 2;
                if(temp < array[mid]){
                    high = mid - 1;
                }else{
                    low = mid + 1;
                }
            }
            //统一移动元素，然后将这个元素插入到正确的位置
            for(int j = i; j >= low + 1; j--){
                array[j] = array[j - 1];
            }
            array[low] = temp;
            System.out.println("第"+i+"次:"+ Arrays.toString(array));
        }
    }

    /**
     * 希尔排序
     * @param array 待排序的数组
     */
    public static void shellSort(int[] array){
        //若干趟扫描，控制增量，增量减半
        for (int i = array.length/2;i>0;i/=2){
            //一趟分若干组，每组进行直接插入排序
            for (int j = i; j < array.length; j++) {
                //temp是当前待排序插入的元素
                int temp = array[j],m;
                //每组元素相距距离为i，寻找插入位置
                for (m = j-i; m >= 0 && temp < array[m]; m-=i) {
                    //插入元素
                    array[m+i] = array[m];
                }
                array[m+i] = temp;
            }
            System.out.println("第"+i+"次:"+ Arrays.toString(array));
        }
    }
}
