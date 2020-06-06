package com.xz.hashtable;

import java.util.Arrays;

/**
 * @Package: com.xz.hashtable
 * @ClassName: Demo
 * @Author: xz
 * @Date: 2020/5/30 18:08
 * @Version: 1.0
 */
public class Demo {

    public static void main(String[] args) {
        HashTab hashTab = new HashTab(5);
        hashTab.add(new Emp(1,"张三"));
        hashTab.add(new Emp(2,"李四"));
        hashTab.add(new Emp(3,"王五"));
        System.out.println(hashTab.get(1));
        System.out.println(hashTab.get(2));
        System.out.println(hashTab.get(3));
    }
}
class HashTab{
    private EmpLinkedList[] empLinkedLists;

    public HashTab(int size) {
        empLinkedLists = new EmpLinkedList[size];
        Arrays.fill(empLinkedLists,new EmpLinkedList());
    }

    public void add(Emp e){
        int hash = hash(e.id);
        empLinkedLists[hash].add(e);
    }

    public void list(){
        for (EmpLinkedList empLinkedList : empLinkedLists) {
            empLinkedList.list();
        }
    }

    public Emp get(int id){
        int hash = hash(id);
        return empLinkedLists[hash].get(id);
    }

    private int hash(int id){
        return id % empLinkedLists.length;
    }
}
class Emp{
    public int id;
    public String name;
    public Emp next;

    public Emp(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Emp{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
class EmpLinkedList{
    public Emp head;
    public void add(Emp e){
        if(head == null){
            head = e;
            return;
        }
        Emp temp = head;
        while(temp.next != null){
            temp = temp.next;
        }
        temp.next = e;
    }

    public void list(){
        if(head == null){
            System.out.println("没有元素");
            return;
        }
        Emp temp = head;
        do {
            System.out.println(temp);
            temp = temp.next;
        }while (temp != null);
    }

    public Emp get(int id){
        Emp temp = head;
        while(temp != null){
            if(temp.id == id){
                return temp;
            }
            temp = temp.next;
        }
        System.out.println("没有该员工");
        return null;
    }
}
