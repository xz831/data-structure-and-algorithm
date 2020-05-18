package com.xz.arrayqueue;

/**
 * @Package: com.xz.arrayqueue
 * @ClassName: Demo
 * @Author: xz
 * @Date: 2020/5/16 18:33
 * @Version: 1.0
 */
public class Demo2 {
    public static void main(String[] args) {
        ArrayQueue2 arrayQueue2 = new ArrayQueue2(4);
        arrayQueue2.add(5);
        arrayQueue2.add(10);
        arrayQueue2.add(15);
        System.out.println(arrayQueue2.get());
        arrayQueue2.showList();
        arrayQueue2.add(20);
        arrayQueue2.showList();
        arrayQueue2.get();
        arrayQueue2.add(30);
        arrayQueue2.showList();
    }
}

/**
 * 环形队列
 */
class ArrayQueue2{
    /**
     * 最大容量
     */
    private final int maxSize;
    /**
     * 队列头的位置
     */
    private int front;
    /**
     * 队列尾部的后一个位置
     */
    private int rear;
    /**
     * 容器实体
     */
    private int[] arr;

    public ArrayQueue2(int maxSize) {
        this.maxSize = maxSize;
        arr = new int[maxSize];
    }

    public boolean isFull(){
        return (rear + 1) % maxSize == front;
    }

    public boolean isEmpty(){
        return rear == front;
    }

    public void add(int n){
        if(isFull()){
            throw new RuntimeException();
        }
        arr[rear] = n;
        rear = (rear + 1) % maxSize;
    }

    public int get(){
        if(isEmpty()){
            throw new RuntimeException();
        }
        int i = arr[front];
        front = (front + 1) % maxSize;
        return i;
    }

    public void showList(){
        //从front开始遍历，遍历多少个元素
        for(int i = front ; i < front + size();i++){
            System.out.printf("%d\t",arr[i % maxSize]);
        }
        System.out.println();
    }

    public int size(){
        return (rear + maxSize - front) % maxSize;
    }

    public int first(){
        if(isEmpty()){
            throw new RuntimeException();
        }
        return arr[front];
    }
}
