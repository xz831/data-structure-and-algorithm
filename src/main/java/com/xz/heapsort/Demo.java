package com.xz.heapsort;

import java.util.Random;

/**
 * @Package: com.xz.heapsort
 * @ClassName: Demo
 * @Author: xz
 * @Date: 2020/6/6 10:25
 * @Version: 1.0
 */
public class Demo {

    public static void main(String[] args) {
        int[] arr = new int[800000];
        Random random = new Random();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(arr.length * 5);
        }
        long l = System.currentTimeMillis();
        sort(arr,"1");
        System.out.println(System.currentTimeMillis()-l);
//        sort(arr,"2");
    }


    private static void sort(int[] arr,String type){
        if("1".equals(type)){
            for(int i = arr.length/2-1;i>=0;i--){
                bigAdjustHeap(arr,i,arr.length);
            }
            int temp;
            for(int i = arr.length - 1;i>0;i--){
                temp=arr[0];
                arr[0] = arr[i];
                arr[i] = temp;
                bigAdjustHeap(arr,0,i);
            }
//            System.out.println(Arrays.toString(arr));
        }else if("2".equals(type)){
            for(int i = arr.length/2-1;i>=0;i--){
                smallAdjustHeap(arr,i,arr.length);
            }
            int temp;
            for(int i = arr.length - 1;i>0;i--){
                temp=arr[0];
                arr[0] = arr[i];
                arr[i] = temp;
                smallAdjustHeap(arr,0,i);
            }
//            System.out.println(Arrays.toString(arr));
        }
    }


    /**
     * @param arr    数组
     * @param i      非叶子节点的索引位置
     * @param length 需要调整数组的长度
     */
    private static void bigAdjustHeap(int[] arr, int i, int length) {
        int temp = arr[i];
        for (int k = 2 * i + 1; k < length; k = 2 * k + 1) {
            if (k + 1 < length && arr[k] < arr[k + 1]) {
                k++;
            }
            if (arr[k] > temp) {
                arr[i] = arr[k];
                i = k;
            } else {
                break;
            }
        }
        arr[i] = temp;
    }

    private static void smallAdjustHeap(int[] arr, int i, int length) {
        int temp = arr[i];
        for (int j = 2 * i + 1; j < length; j = 2 * j + 1) {
            if (j + 1 < length && arr[j] > arr[j + 1]) {
                j++;
            }
            if (temp > arr[j]) {
                arr[i] = arr[j];
                i = j;
            }else{
                break;
            }
        }
        arr[i] = temp;
    }
}
