package com.item.cases;

import com.item.Case;
import com.item.annotations.Benchmark;
import com.item.annotations.Measurement;

import java.util.*;

/**
 * @author: ChangYajie
 * @date: 2019/8/14
 *
 * 1.快速排序 和 归并排序的差别
 * 2.自己实现的归并排序 和 Arrays.sort对比
 * 3.TODO:自己实现一个并发排序（ForkJoin）和Arrays.parallelshell
 *
 */

@Measurement(iterations = 10000,group = 5)
public class SortCase implements Case {

    public static void quickSort(int [] arr,int left,int right) {
        int pivot=0;
        if(left<right) {
            pivot=partition(arr,left,right);
            quickSort(arr,left,pivot-1);
            quickSort(arr,pivot+1,right);
        }
    }
    private static int partition(int[] arr,int left,int right) {
        int key = arr[left];
        while (left < right) {
            while (left < right && arr[right] >= key) {
                right--;
            }
            arr[left] = arr[right];
            while (left < right && arr[left] <= key) {
                left++;
            }
            arr[right] = arr[left];
        }
        arr[left] = key;
        return left;
    }

    public static int[] mergeSort(int[] nums, int left, int right) {
        if (left==right)
            return new int[] { nums[left] };

        int mid = left+ (right - left) / 2;
        int[] leftArr = mergeSort(nums, left, mid); //左有序数组
        int[] rightArr = mergeSort(nums, mid + 1, right); //右有序数组
        int[] newNum = new int[leftArr.length + rightArr.length]; //新有序数组

        int m = 0, i = 0, j = 0;
        while (i < leftArr.length && j < rightArr.length) {
            newNum[m++] = leftArr[i] < rightArr[j] ? leftArr[i++] : rightArr[j++];
        }
        while (i < leftArr.length)
            newNum[m++] = leftArr[i++];
        while (j < rightArr.length)
            newNum[m++] = rightArr[j++];
        return newNum;
    }
    @Benchmark
    public void testQuickSort(){
        int[] a=new int[1000];
        Random random=new Random(201907023);
        for(int i=0;i<a.length;i++){
            a[i]=random.nextInt(1000);
        }
        quickSort(a,0,a.length-1);
    }

    @Benchmark
    //@Measurement(iterations = 5,group = 2)
    public void testmergeSort(){
        int[] a=new int[1000];
        Random random=new Random(201907023);
        for(int i=0;i<a.length;i++){
            a[i]=random.nextInt(1000);
        }
        mergeSort(a,0,a.length-1);
    }
    @Benchmark
    public void testArraySort(){
        int[] a=new int[1000];
        Random random=new Random(201907023);
        for(int i=0;i<a.length;i++){
            a[i]=random.nextInt(1000);
        }
        Arrays.sort(a);
    }

    public void testCollectionsSort(){
        List<Integer> a=new ArrayList<Integer>();
        Random random=new Random(201907023);
        for(int i=0;i<a.size();i++){
            a.add(random.nextInt(1000));
        }
        Collections.sort(a);
    }
}
