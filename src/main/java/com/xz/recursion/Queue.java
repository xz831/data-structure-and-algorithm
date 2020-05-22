package com.xz.recursion;

import java.util.Arrays;

/**
 * @Package: com.xz.recursion
 * @ClassName: Queue
 * @Author: xz
 * @Date: 2020/5/22 21:50
 * @Version: 1.0
 */
public class Queue {
    static int max = 8;
    //下标代表行，值代表列
    static int[] array = new int[max];
    static int count = 0;
    public static void main(String[] args) {
        setQueue(0);
        System.out.println(count);
    }

    /**
     * 放置皇后
     * @param index
     */
    private static void setQueue(int index){
        if(index == 8){
            System.out.println(Arrays.toString(array));
            count++;
            return;
        }
        //8个位置
        for (int i = 0; i < max; i++) {
            array[index] = i;
            if(conflict(index)){
                setQueue(index+1);
            }
        }
    }

    /**
     * 冲突判断
     * @param index 第几个皇后
     * @return
     */
    private static boolean conflict(int index){
        for(int i = 0 ; i < index ; i++){
            //第一个判断是否同一列
            //判断是否同一斜线，这里可以看做是横纵差值不能相等，也可以理解为斜率不能为1
            if(array[i] == array[index] || Math.abs(index-i) == Math.abs(array[index]-array[i])){
                return false;
            }
        }
        return true;
    }
}
