package com.test.TestSort;

import com.util.tools.JSonUtils;

/**
 * 递归和排序算法
 * Created by 志华 on 2016/4/19.
 */
public class TSort {


    public static void main(String[] args) {
        //递归算法测试
//        System.out.println(getSum(3));
        // 冒泡排序 n*n-1/2  从小到大
        int[] array = {17, 14, 24, 11, 13, 15};
//        swap(array);
        //快速排序
        System.out.println(JSonUtils.toJSONString(array));
        quicksort(array, 0, array.length - 1);
        System.out.println(JSonUtils.toJSONString(array));

     /*  int count = 0 ;
        for(int k = 0 ;k<10;k++){
            System.out.println(++count);
        }*/


    }


    //=====================================递归算法================================================

    /**
     * 递归算法 1*1+2*2+3*3+...+n*n
     *
     * @param n
     * @return
     */
    public static int getSum(int n) {
        int k = 0;
        if (n > 0) {
            k += n * n;
            n--;
            System.out.println(k);
            k += getSum(n);
        }
        return k;
    }

    //=====================================冒泡排序================================================
    public static void swap(int[] array) {
        for (int i = 1; i < array.length; i++) {
            for (int j = 0; j < array.length - i; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }

    }

    // ===================================快速排序====================================
    static void quicksort(int n[], int left, int right) {
        int dp;
        if (left < right) {
            dp = partition(n, left, right);
            System.out.println("bp is :" + dp);
            quicksort(n, left, dp - 1);
            quicksort(n, dp + 1, right);
        }
    }

    //i++ i-- 是有返回值的，为原先值
    static int partition(int n[], int left, int right) {
        int pivot = n[left];
        while (left < right) {
            while (left < right && n[right] >= pivot)
                right--;
            if (left < right)
               /* n[left]=n[right];
                left++*/ ;
            n[left++] = n[right];
            while (left < right && n[left] <= pivot)
                left++;
            if (left < right)
                n[right--] = n[left];
        }
        n[left] = pivot;
        return left;
    }


}



