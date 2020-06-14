package com.xz.knapsackproblem;

import java.lang.reflect.Array;
import java.lang.reflect.WildcardType;
import java.util.Arrays;

/**
 * @Package: com.xz.knapsackproblem
 * @ClassName: Demo
 * @Author: xz
 * @Date: 2020/6/14 13:29
 * @Version: 1.0
 */
public class Demo {
    public static void main(String[] args) {
        int[] price = {1500, 2000,3000};
        int[] weight = {1, 3, 4};
        int size = 3;
        int n = price.length;
        int[][] v = new int[n + 1][size + 1];
        //放入的路径
        int[][] path = new int[n + 1][size + 1];
        for (int i = 1; i < v.length; i++) {
            for (int j = 1; j < v[i].length; j++) {
                if (weight[i - 1] > j) {
                    v[i][j] = v[i - 1][j];
                } else {
                    if (v[i - 1][j] < v[i - 1][j - weight[i - 1]] + price[i - 1]) {
                        v[i][j] = v[i - 1][j - weight[i - 1]] + price[i - 1];
                        path[i][j] = 1;
                    } else {
                        v[i][j] = v[i - 1][j];
                    }
                }
            }
        }
//        int max = 0 ;
//        int maxIndex = 0;
//        for (int i = 0; i < v.length; i++) {
//            if(max < v[i][size]){
//                max = v[i][size];
//                maxIndex = i;
//            }
//        }
//        System.out.println(max);
//        System.out.println(maxIndex);
        //从最后遍历路径
        int i = path.length - 1;
        int j = path[0].length - 1;
        while (i > 0 && j > 0) {
            if (path[i][j] == 1) {
                System.out.println("把第 " + i + " 商品放入背包");
                j -= weight[i - 1];
            }
            i--;
        }
    }
}
