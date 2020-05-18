package com.xz.arrayqueue;

import java.util.Arrays;

/**
 * @Package: com.xz.arrayqueue
 * @ClassName: Demo
 * @Author: xz
 * @Date: 2020/5/16 18:33
 * @Version: 1.0
 */
public class Demo {
    public static void main(String[] args) {
        ArrayQueue arrayQueue = new ArrayQueue(5);
        arrayQueue.add(10);
        arrayQueue.add(20);
        arrayQueue.showList();
        System.out.println(arrayQueue.first());
        System.out.println(arrayQueue.get());
        System.out.println(arrayQueue.get());
    }
}
class ArrayQueue{
    /**
     * 最大容量
     */
    private final int maxSize;
    /**
     * 队列头的前一个位置
     */

    private int front;
    /**
     * 队列尾部
     */
    private int rear;
    /**
     * 容器实体
     */
    private int[] arr;

    public ArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        arr = new int[maxSize];
        front = -1;
        rear = -1;
    }

    public boolean isFull(){
        return rear == maxSize - 1;
    }

    public boolean isEmpty(){
        return rear == front;
    }

    public void add(int n){
        if(isFull()){
            throw new RuntimeException();
        }
        arr[++rear] = n;
    }

    public int get(){
        if(isEmpty()){
            throw new RuntimeException();
        }
        return arr[++front];
    }

    public void showList(){
        System.out.println(Arrays.toString(arr));
    }

    public int first(){
        if(isEmpty()){
            throw new RuntimeException();
        }
        return arr[front+1];
    }
}
