package com.xz.stackcalculation;

/**
 * @Package: com.xz.stackcalculation
 * @ClassName: Demo
 * @Author: xz
 * @Date: 2020/5/19 16:48
 * @Version: 1.0
 */
public class Demo {
    /**
     * 负数缺陷
     * @param args
     */
    public static void main(String[] args) {
        System.out.println( 2-2*2-3+ "等于" + calculation("2-2*2-3"));
    }

    public static int calculation(String str) {
        char[] chars = str.toCharArray();
        LinkedStack<Integer> numStack = new LinkedStack<>();
        LinkedStack<Character> symbolStack = new LinkedStack<>();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] <= 57 && chars[i] >= 48) {
                int i1 = chars[i] - '0';
                while (i + 1 < chars.length && chars[i + 1] <= 57 && chars[i + 1] >= 48) {
                    i1 = i1 * 10 + (chars[++i] - '0');
                }
                numStack.push(i1);
            } else {
                Character head = symbolStack.peek();
                if (head == null) {
                    symbolStack.push(chars[i]);
                } else {
                    if (highLevel(chars[i], head)) {
                        symbolStack.push(chars[i]);
                    } else {
                        Character pop = symbolStack.pop();
                        Integer pop1 = numStack.pop();
                        Integer pop2 = numStack.pop();
                        int result;
                        switch (pop) {
                            case '+':
                                result = pop2 + pop1;
                                break;
                            case '-':
                                result = pop2 - pop1;
                                break;
                            case '*':
                                result = pop2 * pop1;
                                break;
                            case '/':
                                result = pop2 / pop1;
                                break;
                            default:
                                throw new RuntimeException("非法运算符");
                        }
                        numStack.push(result);
                        symbolStack.push(chars[i]);
                    }
                }
            }
        }
        while (numStack.size() != 1) {
            Integer pop1 = numStack.pop();
            Integer pop2 = numStack.pop();
            Character symbol = symbolStack.pop();
            int result;
            switch (symbol) {
                case '+':
                    result = pop2 + pop1;
                    break;
                case '-':
                    result = pop2 - pop1;
                    break;
                case '*':
                    result = pop2 * pop1;
                    break;
                case '/':
                    result = pop2 / pop1;
                    break;
                default:
                    throw new RuntimeException("非法运算符");
            }
            numStack.push(result);
        }
        return numStack.pop();
    }

    public static boolean highLevel(char a, char b) {
        return (a == '*' || a == '/') && (b == '+' || b == '-');
    }
}

class LinkedStack<T> {
    private Node<T> head;

    public void push(T t) {
        if (head == null) {
            head = new Node<>(t);
            return;
        }
        Node<T> temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = new Node<>(t);
    }

    public T peek() {
        if (head == null) {
            System.out.println("栈为空");
            return null;
        }
        Node<T> temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        return temp.getT();
    }

    public T pop() {
        if (head == null) {
            System.out.println("栈为空");
            return null;
        }
        Node<T> temp = head;
        if (temp.next == null) {
            head = null;
            return temp.getT();
        }
        while (temp.next.next != null) {
            temp = temp.next;
        }
        Node<T> next = temp.next;
        temp.next = null;
        return next.getT();
    }

    public int size() {
        if (head == null) {
            return 0;
        }
        int count = 0;
        Node<T> temp = head;
        while (temp != null) {
            temp = temp.next;
            count++;
        }
        return count;
    }
}

class Node<T> {
    private final T t;

    public Node<T> next;

    public Node(T t) {
        this.t = t;
    }

    public T getT() {
        return t;
    }
}
