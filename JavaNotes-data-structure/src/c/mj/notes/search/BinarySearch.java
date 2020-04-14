package c.mj.notes.search;

/**
 * 折半查找算法
 * @author ChenMJ
 * @version BinarySearch.class, v 0.1 2020/4/13 18:34  Exp$
 */
public class BinarySearch {
    /**
     * 二分查找
     * @param arr 要进行查找的数组，要求数组必须是有序的
     * @param findElem 要查找的元素
     * @return 返回要查找的元素在数组的索引位置， 返回-1表示没找到
     */
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

    public static void main(String[] args) {
        BinarySearch bs = new BinarySearch();
        int[] arr = {1,3,6,9,12,23,33,44,45,67,78,98,100,111,123,1234,2345,9999,11111,12345,666666,900000};
        int res = bs.binarySearch(arr, 44);
        System.out.println("res=" + res);
    }
}
