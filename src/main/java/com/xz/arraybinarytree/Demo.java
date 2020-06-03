package com.xz.arraybinarytree;

/**
 * @Package: com.xz.arrbinarytree
 * @ClassName: Demo
 * @Author: xz
 * @Date: 2020/6/2 11:42
 * @Version: 1.0
 */
public class Demo {
    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6,7};
        ArrayBinaryTree arrayBinaryTree = new ArrayBinaryTree(arr);
        arrayBinaryTree.preOrder();
    }
}
class ArrayBinaryTree{
    int[] arr;

    public ArrayBinaryTree(int[] arr) {
        this.arr = arr;
    }

    public void preOrder(){
        if(arr == null || arr.length == 0){
            return;
        }
        preOrder(0);
    }
    private void preOrder(int index){
        System.out.println(arr[index]);
        if(index * 2 + 1 < arr.length){
            preOrder(index * 2 + 1);
        }
        if(index * 2 + 2 < arr.length){
            preOrder(index * 2 + 2);
        }
    }
}
