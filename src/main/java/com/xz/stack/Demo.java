package com.xz.stack;

/**
 * @Package: com.xz.stack
 * @ClassName: Demo
 * @Author: xz
 * @Date: 2020/5/19 14:44
 * @Version: 1.0
 */
public class Demo {
    public static void main(String[] args) {
        MyStack myStack = new MyStack();
        for (int i = 0; i < 1024; i++) {
            myStack.push(i);
        }
        for (int i = 0; i < 10; i++) {
            System.out.println(myStack.pop());
        }
        myStack.pop();
        System.out.println("-------");
        LinkedStack linkedStack = new LinkedStack();
        for (int i = 0;i < 1024 ; i++){
            Node node = new Node(i,i+"娃");
            linkedStack.push(node);
        }
        for (int i = 0;i < 1024 ; i++){
            System.out.println(linkedStack.pop());
        }
    }
}

class MyStack {
    private int index = -1;
    private final int[] stack = new int[1024];

    public void push(int i) {
        if(index >= 1023){
            throw new RuntimeException("栈溢出");
        }
        stack[++index] = i;
    }

    public int pop() {
        if (index < 0) {
            throw new RuntimeException("栈为空");
        }
        return stack[index--];
    }
}
class LinkedStack{
    private Node head;
    public void push(Node n){
        if(head == null){
            head = n;
            return;
        }
        Node temp = head;
        while(temp.next != null){
            temp = temp.next;
        }
        temp.next = n;
    }

    public Node pop(){
        if(head == null){
            System.out.println("栈为空");
            return null;
        }
        Node temp = head;
        if(temp.next == null){
            head = null;
            return temp;
        }
        while(temp.next.next != null){
            temp = temp.next;
        }
        Node next = temp.next;
        temp.next = null;
        return next;
    }
}
class Node{
    private int id;
    private String name;
    public Node next;

    public Node(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Node{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
