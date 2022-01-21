package c.mj.notes.algorithm;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Arrays;


/**
 * create class SortTest.java @version 1.0.0 by @author ChenMJ @date 2022-01-07 16:05:00
 */
@Slf4j(topic = "C.MJ.NOTES")
public class SortTest {

    @Test
    public void basicSortTest(){
        long begin = System.currentTimeMillis();
        int array[] = {6,2,5,1,7,3,8,4,9,0};
        Sort.bubbleSort(array);
        long end = System.currentTimeMillis();
        log.debug("基数排序的数组:{}，共耗时:{}",array,end-begin);
    }

    @Test
    public void quickSortTest(){
        long begin = System.currentTimeMillis();
        int array[] = {6,2,5,1,7,3,8,4,9,0};
        Sort.quickSort(array,array.length,array.length-1);
        long end = System.currentTimeMillis();
        log.debug("快速排序的数组:{}，共耗时:{}",array,end-begin);
    }

    @Test
    public void selectSortTest(){
        long begin = System.currentTimeMillis();
        int array[] = {6,2,5,1,7,3,8,4,9,0};
        Sort.selectSort(array);
        long end = System.currentTimeMillis();
        log.debug("选择排序的数组:{}，共耗时:{}",array,end-begin);
    }

    @Test
    public void shellSortTest(){
        long begin = System.currentTimeMillis();
        int array[] = {6,2,5,1,7,3,8,4,9,0};
        Sort.shellSort(array);
        long end = System.currentTimeMillis();
        log.debug("希尔排序的数组:{}，共耗时:{}",array,end-begin);
    }

    @Test
    public void insertionSortTest(){
        int array[] = {6,2,5,1,7,3,8,4,9,0};
        Sort.bubbleSort(array);
        log.debug("插入排序的数组:{}",array);
    }

    @Test
    public void bubbleSortTest(){
        int array[] = {6,2,5,1,7,3,8,4,9,0};
        Sort.bubbleSort(array);
        log.debug("冒泡排序的数组:{}",array);
    }
}