package com.xz.doublelinkedlist;

/**
 * @Package: com.xz.doublelinkedlist
 * @ClassName: Demo
 * @Author: xz
 * @Date: 2020/5/18 10:11
 * @Version: 1.0
 */
public class Demo {
    public static void main(String[] args) {
        DoubleLinkedList list = new DoubleLinkedList();
        Node node1 = new Node(1,"宋江");
        Node node2 = new Node(2,"卢俊义");
        Node node3 = new Node(3,"吴用");
        Node node4 = new Node(4,"林冲");
        list.add(node1);
        list.add(node3);
        list.addById(node2);
        list.addById(node4);
        list.list();
        list.delById(3);
        list.list();
    }
}

class DoubleLinkedList{

    public Node head;

    public void add(Node n){
        if(head == null){
            head = n;
            return;
        }
        Node temp = head;
        while(temp.next != null){
            temp = temp.next;
        }
        temp.next = n;
        n.pre = temp;
    }

    public void addById(Node n){
        if(head == null){
            head = n;
            return ;
        }
        Node temp = head;
        boolean flag = false;
        while (temp.next != null){
            if(temp.next.id > n.id){
                flag = true;
                break;
            }else if(temp.next.id == n.id){
                System.out.println(n.id+"已存在无法添加");
                return;
            }
            temp = temp.next;
        }
        if(flag){
            Node next = temp.next;
            temp.next = n;
            n.pre = temp;
            next.pre = n;
            n.next = next;
        }else{
            temp.next = n;
            n.pre = temp;
        }
    }

    public void delById(int id){
        if(head == null){
            System.out.println("队列为空");
            return;
        }
        Node temp = head;
        boolean flag = false;
        while(temp != null){
            if(temp.id == id){
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if(flag){
            temp.pre.next = temp.next;
            if(temp.next != null){
                temp.next.pre = temp.pre;
            }
        }else {
            System.out.println("没有找到该id");
        }
    }

    public void list(){
        if(head == null){
            System.out.println("队列为空");
        }
        Node temp = head;
        System.out.println("打印list");
        while(temp != null){
            System.out.println(temp);
            temp = temp.next;
        }
        System.out.println("打印list完成");
    }
}

class Node{
    public int id;
    public String name;
    public Node pre;
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
