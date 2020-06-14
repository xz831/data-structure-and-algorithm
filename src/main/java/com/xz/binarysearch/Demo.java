package com.xz.binarysearch;

import java.util.Arrays;
import java.util.Random;

/**
 * @Package: com.xz.binarysearch
 * @ClassName: Demo
 * @Author: xz
 * @Date: 2020/6/13 17:16
 * @Version: 1.0
 */
public class Demo {

    public static void main(String[] args) {
        int size = 20;
        int[] arr = new int[size];
        Random random = new Random();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(size);
        }
        Arrays.sort(arr);
        System.out.println(Arrays.toString(arr));
        int i = random.nextInt(size);
        System.out.println(i);
        System.out.println(binarySearchIndex(arr, i));
    }

    public static int binarySearchIndex(int[] arr,int value){

        int start = 0;
        int end = arr.length-1;
        while(start <= end){
            int mid = (start+end)/2;
            if(arr[mid]==value){
                return mid;
            }
            if(arr[mid]<value){
                start = mid+1;
            }
            if(arr[mid]>value){
                end = mid-1;
            }
        }
        return -1;
    }
}
