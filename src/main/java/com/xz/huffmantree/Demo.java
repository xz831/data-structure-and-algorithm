package com.xz.huffmantree;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @Package: com.xz.huffmantree
 * @ClassName: Demo
 * @Author: xz
 * @Date: 2020/6/6 16:04
 * @Version: 1.0
 */
public class Demo {

    public static void main(String[] args) {
        int[] arr = {13,7,8,3,29,6,1};
        Node node = buildHuffmanTree(arr);
        node.preOrder();
    }

    private static Node buildHuffmanTree(int[] arr){
        List<Node> list = new ArrayList<>();
        for (int i : arr) {
            list.add(new Node(i));
        }
        while(list.size() != 1){
            list.sort(Comparator.comparingInt(o -> o.value));
            Node left = list.get(0);
            Node right = list.get(1);
            Node parent = new Node(left.value+right.value);
            parent.left = left;
            parent.right = right;
            list.add(parent);
            list.remove(left);
            list.remove(right);
        }
        return list.get(0);
    }
}
class Node{
    int value;
    Node right;
    Node left;

    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    public void preOrder(){
        System.out.println(this);
        if(left != null){
            left.preOrder();
        }
        if(right!=null){
            right.preOrder();
        }
    }
}
