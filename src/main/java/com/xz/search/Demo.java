package com.xz.search;

import javax.swing.text.Highlighter;
import java.util.Arrays;
import java.util.Random;

/**
 * @Package: com.xz.search
 * @ClassName: Demo
 * @Author: xz
 * @Date: 2020/5/29 10:28
 * @Version: 1.0
 */
public class Demo {
    static int[] arr;
    static int maxSize = 30;

    static {
        Random random = new Random();
        arr = new int[maxSize];
        for (int i = 0; i < maxSize; i++) {
            arr[i] = random.nextInt(maxSize * 2);
        }
    }
    public static void main(String[] args) {
//        arr = new int[]{1,2,3,4,5,6,7,8,9,10};
        Arrays.sort(arr);
        System.out.println(Arrays.toString(arr));
//        System.out.println(insertSearch(5));
        System.out.println(fibonacciSearch(12));
    }

    private static int seqSearch(int a){
        for (int i = 0; i < arr.length; i++) {
            if(arr[i] == a){
                return i;
            }
        }
        return -1;
    }

    private static String binarySearch(int a){
        Arrays.sort(arr);
        int start = 0;
        int end = arr.length - 1;
        while(start <= end){
            int mid = (start + end) / 2;
            if(arr[mid] == a){
                String result = mid+"";
                int temp = mid;
                while(temp > 0){
                    if(arr[temp - 1] == a){
                        result += "," + --temp;
                    }else{
                        break;
                    }
                }
                temp = mid;
                while(temp + 1 < arr.length){
                    if(arr[temp + 1] == a){
                        result += "," + ++temp;
                    }else {
                        break;
                    }
                }
                return result;
            } else if(arr[mid] > a){
                end = mid - 1;
            }else {
                start = mid + 1;
            }
        }
        return "没有找到";
    }

    private static String insertSearch(int a){
        Arrays.sort(arr);
        int start = 0;
        int end = arr.length - 1;
        if(a < arr[start] || a > arr[end]){
            return "没有找到";
        }
        while(start <= end){
            int mid = start + (a - arr[start]) * (end - start) / (arr[end] - arr[start]);
            if(arr[mid] == a){
                String result = mid+"";
                int temp = mid;
                while(temp > 0){
                    if(arr[temp - 1] == a){
                        result += "," + --temp;
                    }else{
                        break;
                    }
                }
                temp = mid;
                while(temp + 1 < arr.length){
                    if(arr[temp + 1] == a){
                        result += "," + ++temp;
                    }else {
                        break;
                    }
                }
                return result;
            } else if(arr[mid] > a){
                end = mid - 1;
            }else {
                start = mid + 1;
            }
        }
        return "没有找到";
    }

    private static int fibonacciSearch(int a){
        int[] fibonacciArr = new int[30];
        fibonacciArr[0] = 1;
        fibonacciArr[1] = 1;
        for(int i =2 ; i < 30 ; i++){
            fibonacciArr[i] = fibonacciArr[i-1] + fibonacciArr[i-2];
        }
        int start = 0;
        int end = arr.length - 1;
        int k = 0;
        while(end > fibonacciArr[k]){
            k++;
        }
        int[] newArr = Arrays.copyOf(arr, fibonacciArr[k]);
        for(int i = end + 1; i < newArr.length ; i++){
            newArr[i] = arr[end];
        }
        while(start <= end){
            int mid = start + fibonacciArr[k - 1] - 1;
            if(newArr[mid] == a){
                if(mid <= end){
                    return mid;
                }else{
                    return end;
                }
            }else if(newArr[mid] < a){
                start = mid + 1;
                k--;
            }else{
                end = mid - 1;
                k -= 2;
            }
        }
        return -1;
    }
}
