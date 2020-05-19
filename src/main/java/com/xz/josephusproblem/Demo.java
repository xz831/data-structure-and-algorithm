package com.xz.josephusproblem;

/**
 * @Package: com.xz.josephusproblem
 * @ClassName: Demo
 * @Author: xz
 * @Date: 2020/5/18 14:36
 * @Version: 1.0
 */
public class Demo {

    public static void main(String[] args) {
        CircleSingleLinkedList singleLinkedList = new CircleSingleLinkedList();
        Node node1 = new Node(1, "宋江");
        Node node2 = new Node(2, "卢俊义");
        Node node3 = new Node(3, "吴用");
        Node node4 = new Node(4, "公孙胜");
        Node node5 = new Node(5, "关胜");
        Node node6 = new Node(6, "林冲");
        Node node7 = new Node(7, "秦明");
        Node node8 = new Node(8, "呼延灼");
        singleLinkedList.add(node1);
        singleLinkedList.add(node2);
        singleLinkedList.add(node3);
        singleLinkedList.add(node4);
        singleLinkedList.add(node5);
        singleLinkedList.add(node6);
        singleLinkedList.add(node7);
        singleLinkedList.add(node8);
        singleLinkedList.list();
        singleLinkedList.josephusProblem(1,3);

    }
}

class CircleSingleLinkedList{
    private Node head;

    public void add(Node node){
        if(node == null){
            System.out.println("传入结点不能为空");
            return;
        }
        if(head == null){
            head = node;
            return;
        }
        Node temp = head;
        while(temp.next != head){
            temp = temp.next;
        }
        temp.next = node;
        node.next = head;
    }

    public void list(){
        if(head == null){
            System.out.println("列表为空");
            return;
        }
        Node temp = head;
        System.out.println("列表打印");
        do {
            System.out.println(temp);
            temp = temp.next;
        }while (temp != head);
        System.out.println("列表打印完毕");
    }

    public void josephusProblem(int start,int step){
        if(head == null){
            System.out.println("列表为空");
            return;
        }
        if(start < 1 || step < 1){
            System.out.println("参数有误");
            return;
        }
        Node temp = head;
        do{
            temp = temp.next;
        }
        while(temp.next != head);
        //初始化指针位置
        for(int i = 0 ; i < start - 1; i++){
            head = head.next;
            temp = temp.next;
        }
        while(temp != head){
            for(int i = 0 ; i < step - 1 ; i++){
                temp = temp.next;
                head = head.next;
            }
            System.out.println(head+" 出圈");
            head = head.next;
            temp.next = head;
        }
        System.out.println(temp+" 最后出圈");
    }
}

class Node{
    private int id;
    private String name;
    public Node next = this;

    public Node(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
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