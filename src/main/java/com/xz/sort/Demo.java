package com.xz.sort;

import java.util.Arrays;
import java.util.Random;

/**
 * @Package: com.xz.sort
 * @ClassName: Demo
 * @Author: xz
 * @Date: 2020/5/24 14:13
 * @Version: 1.0
 */
public class Demo {

    static int[] arr;
    static int maxSize = 10;

    static {
        Random random = new Random();
        arr = new int[maxSize];
        for (int i = 0; i < maxSize; i++) {
            arr[i] = random.nextInt(maxSize * 10);
        }
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
//        bubbleSort(); //8W数据 10578ms
//        selectionSort();//8W数据 2500ms
//        insertSort();//8W数据 500ms
//        shellSort();//8W数据 30ms
//        quickSort(0, arr.length - 1);//8W数据 13ms
        int[] ints = mergeSort(arr, 0, arr.length - 1);
        System.out.println(System.currentTimeMillis() - start);
//        System.out.println(Arrays.toString(ints));
    }

    /**
     * 冒泡
     */
    private static void bubbleSort() {
        //优化，提高效率，如果一次遍历后，没有发生替换，则说明该数组已经有序，直接返回即可
        boolean flag;
        for (int i = maxSize - 1; i > 0; i--) {
            flag = true;
            for (int j = 0; j < i; j++) {
                if (arr[j] > arr[j + 1]) {
                    flag = false;
                    swap(j, j + 1);
                }
            }
            if (flag) {
                break;
            }
        }
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 选择
     */
    private static void selectionSort() {
        int num;
        int index;
        for (int i = 0; i < maxSize - 1; i++) {
            num = arr[i];
            index = i;
            for (int j = i + 1; j < maxSize; j++) {
                if (num > arr[j]) {
                    num = arr[j];
                    index = j;
                }
            }
            if (index != i) {
                swap(i, index);
            }
        }
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 插入
     */
    private static void insertSort() {
        int[] newArr = new int[arr.length];
        newArr[0] = arr[0];
        int insertValue;
        int insertIndex;
        for (int i = 1; i < arr.length; i++) {
            insertValue = arr[i];
            insertIndex = i - 1;
            while (insertIndex >= 0 && newArr[insertIndex] > insertValue) {
                newArr[insertIndex + 1] = newArr[insertIndex];
                insertIndex--;
            }
            newArr[insertIndex + 1] = insertValue;
        }
        System.out.println(Arrays.toString(newArr));
    }

    /**
     * 希尔插入法
     */
    private static void shellSort() {
        int step = arr.length / 2;
        while (step != 0) {
            int value;
            int index;
            for (int i = step; i < arr.length; i++) {
//                for(int j = i - step ; j>=0 ; j -= step){
//                    if(arr[j] > arr[j+step]){
//                        swap(j,j+step);
//                    }
//                }
                index = i;
                value = arr[i];
                //按步进直接插入
                while (index - step >= 0 && arr[index - step] > value) {
                    arr[index] = arr[index - step];
                    index -= step;
                }
                arr[index] = value;
            }
            step = step / 2;
        }
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 快速排序
     */
    private static void quickSort(int start, int end) {
        int pivot = arr[start];
        int l = start;
        int r = end;
        while (l < r) {
            while (l < r && arr[r] > pivot) {
                r--;
            }
            while (l < r && arr[l] < pivot) {
                l++;
            }
            if (l < r && arr[r] == arr[l]) {
                r--;
            } else {
                swap(r, l);
            }
        }
        if (l - 1 > start) {
            quickSort(start, l - 1);
        }
        if (r + 1 < end) {
            quickSort(r + 1, end);
        }
    }

    /**
     * 归并排序
     */
    public static int[] mergeSort(int[] nums, int l, int h) {
        if(l == h){
            return new int[]{nums[l]};
        }
        int mid = (l+h)/2;
        int[] left = mergeSort(nums, l, mid);
        int[] right = mergeSort(nums,mid+1,h);
        int [] newArr = new int[left.length+ right.length];
        int a = 0,b = 0,c = 0;
        while(a < left.length && b < right.length){
            newArr[c++] = left[a] < right[b] ? left[a++] : right[b++];
        }
        while(a < left.length){
            newArr[c++] = left[a++];
        }
        while(b < right.length){
            newArr[c++] = right[b++];
        }
        return newArr;
    }

    private static void swap(int x, int y) {
        int temp = arr[x];
        arr[x] = arr[y];
        arr[y] = temp;
    }
}
