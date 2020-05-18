package com.xz.singlelinkedlist;

import java.util.Stack;

/**
 * @Package: com.xz.singlelinkedlist
 * @ClassName: Demo
 * @Author: xz
 * @Date: 2020/5/17 11:48
 * @Version: 1.0
 */
public class Demo {

    public static void main(String[] args) {
        Node node1 = new Node(1,"宋江");
        Node node4 = new Node(4,"林冲");
        Node node2 = new Node(2,"卢俊义");
        Node node3 = new Node(9,"吴用");
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        singleLinkedList.addById(node1);
        singleLinkedList.addById(node2);
        singleLinkedList.addById(node3);
        singleLinkedList.addById(node4);
        singleLinkedList.list();
        System.out.println(singleLinkedList.size());
        System.out.println("-------------");
        Node update1 = new Node(4,"林冲222");
        singleLinkedList.updateById(update1);
        singleLinkedList.list();
        System.out.println(singleLinkedList.size());
        System.out.println("-------------");
        Node update2 = new Node(2,"卢俊义222");
        singleLinkedList.updateById(update2);
        singleLinkedList.list();
        System.out.println(singleLinkedList.size());
        System.out.println("-------------");
        singleLinkedList.delById(3);
        singleLinkedList.list();
        System.out.println(singleLinkedList.size());
        System.out.println(singleLinkedList.getByLastIndex(1));
        System.out.println(singleLinkedList.getByLastIndex(2));
        System.out.println(singleLinkedList.getByLastIndex(3));
        System.out.println(singleLinkedList.getByLastIndex(4));
        System.out.println("-------------");
        singleLinkedList.reverse();
        singleLinkedList.list();
        System.out.println("-------------");
        singleLinkedList.reverse2();
        singleLinkedList.list();
        System.out.println("-------------");
        singleLinkedList.listReverse();
        singleLinkedList.listReverse2();
        System.out.println("-------------");
        Node node5 = new Node(5,"关胜");
        Node node6 = new Node(6,"秦明");
        Node node7 = new Node(7,"呼延灼");
        SingleLinkedList singleLinkedList1 = new SingleLinkedList();
        singleLinkedList1.addById(node5);
        singleLinkedList1.addById(node6);
        singleLinkedList1.addById(node7);
        singleLinkedList.merge(singleLinkedList1);
        singleLinkedList.list();
    }

}
class SingleLinkedList{
    private Node head = new Node(0,"");

    public void add(Node n){
        Node temp = head;
        while(temp.getNext() != null){
            temp = temp.getNext();
        }
        temp.setNext(n);
    }

    public void addById(Node n){
        Node temp = head;
        while(temp.getNext() != null){
            if(temp.getNext().getId() > n.getId()){
                break;
            }else if(temp.getNext().getId() == n.getId()){
                System.out.println(n.getId()+"已存在，无法添加");
                return;
            }
            temp = temp.getNext();
        }
        n.setNext(temp.getNext());
        temp.setNext(n);
    }

    public void updateById(Node n){
        Node temp = head;
        boolean flag = false;
        while(temp.getNext() != null){
            if(temp.getNext().getId() == n.getId()){
                flag = true;
                break;
            }
            temp = temp.getNext();
        }
        if(!flag){
            System.out.println("没有该ID存在");
            return ;
        }
        Node last = temp.getNext().getNext();
        temp.setNext(n);
        n.setNext(last);
    }

    public void delById(int id){
        Node temp = head;
        boolean flag = false;
        while(temp.getNext() != null){
            if(temp.getNext().getId() == id){
                flag = true;
                break;
            }
            temp = temp.getNext();
        }
        if(!flag){
            System.out.println("没有该ID存在");
            return ;
        }
        temp.setNext(temp.getNext().getNext());
    }

    public void list(){
        if(head.getNext() == null){
            System.out.println("链表为空");
            return;
        }
        Node temp = head;
        while (temp.getNext() != null){
            System.out.println(temp = temp.getNext());
        }
    }

    public int size(){
        int size = 0;
        Node temp = head;
        while (temp.getNext() != null){
            temp = temp.getNext();
            size++;
        }
        return size;
    }

    public Node getByLastIndex(int i){
        int index = size() - i + 1;
        if(index <= 0 || index > size()){
            return null;
        }
        Node temp = head;
        int count = 0;
        while (count++ != index){
            temp = temp.getNext();
        }
        return temp;
    }

    public void reverse(){
        if(head.getNext() == null || head.getNext().getNext() == null){
            return;
        }
        Node temp = head;
        Node[] nodes = new Node[size()];
        for(int i = 0 ; i < size() ; i++){
            nodes[i] = temp.getNext();
            temp = temp.getNext();
        }
        head.setNext(nodes[nodes.length-1]);
        for(int i = nodes.length -2 ; i >= 0 ; i--){
            nodes[i+1].setNext(nodes[i]);
        }
        nodes[0].setNext(null);
    }

    public void reverse2(){
        if(head.getNext() == null || head.getNext().getNext() == null){
            return;
        }
        Node reverseHead = new Node(0,"");
        Node cur = head.getNext();
        while (cur != null){
            Node next = cur.getNext();
            cur.setNext(reverseHead.getNext());
            reverseHead.setNext(cur);
            cur = next;
        }
        head.setNext(reverseHead.getNext());
    }

    public void listReverse(){
        print(head);
    }

    private void print(Node node){
        if(node.getNext() != null){
            print(node.getNext());
        }
        if(node != head){
            System.out.println(node);
        }
    }

    public void listReverse2(){
        if(head.getNext() == null){
            return ;
        }
        Stack<Node> stack = new Stack<>();
        Node cur = head.getNext();
        while(cur != null){
            stack.push(cur);
            cur = cur.getNext();
        }
        while(stack.size() > 0){
            System.out.println(stack.pop());
        }
    }

    public void merge(SingleLinkedList list){
        if(list == null){
            throw new RuntimeException("不能为null");
        }
        if(list.head.getNext() == null){
            return;
        }
        Node next = list.head.getNext();
        Node[] nodes = new Node[list.size()];
        for(int i = 0; i < list.size() ; i++){
            nodes[i] = next;
            next = next.getNext();
        }
        for (Node node : nodes) {
            addById(node);
        }
    }
}

class Node{
    private String name;

    private Node next;

    private int id;

    public Node(int id,String name) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Node{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}