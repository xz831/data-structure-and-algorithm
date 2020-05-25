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
    static int maxSize = 80000;

    static {
        Random random = new Random();
        arr = new int[maxSize];
        for (int i = 0; i < maxSize; i++) {
            arr[i] = random.nextInt(maxSize * 5);
        }
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
//        bubbleSort(); //8W数据 10578ms
//        selectionSort();//8W数据 2500ms
//        insertSort();//8W数据 500ms
        shellSort();//8W数据 30ms
        System.out.println(System.currentTimeMillis() - start);
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
    private static void shellSort(){
        int step = arr.length / 2;
        while(step != 0){
            int value;
            int index;
            for(int i = step ; i < arr.length ; i++){
//                for(int j = i - step ; j>=0 ; j -= step){
//                    if(arr[j] > arr[j+step]){
//                        swap(j,j+step);
//                    }
//                }
                index = i;
                value = arr[i];
                //按步进直接插入
                while(index - step >= 0 && arr[index - step] > value){
                    arr[index] = arr[index - step];
                    index -= step;
                }
                arr[index] = value;
            }
            step = step / 2;
        }
        System.out.println(Arrays.toString(arr));
    }

    private static void swap(int x, int y) {
        arr[x] = arr[x] + arr[y];
        arr[y] = arr[x] - arr[y];
        arr[x] = arr[x] - arr[y];
    }
}
