package com.xz.binarysorttree;

import java.util.ArrayList;
import java.util.List;

/**
 * @Package: com.xz.binarysorttree
 * @ClassName: Demo
 * @Author: xz
 * @Date: 2020/6/8 17:31
 * @Version: 1.0
 */
public class Demo {
    public static void main(String[] args) {
        int[] arr = {7, 3, 10, 12, 5, 1, 9, 0};
        List<Node> list = new ArrayList<>();
        for (int i : arr) {
            Node node = new Node(i);
            list.add(node);
        }
        BinarySortTree binarySortTree = new BinarySortTree();
        for (Node node : list) {
            binarySortTree.add(node);
        }
        binarySortTree.del(1);
        binarySortTree.preOrder();
    }
}

class BinarySortTree {
    Node root;

    public void add(Node node) {
        if (root == null) {
            root = node;
            return;
        }
        add(node, root);
    }

    private void add(Node add, Node node) {
        if (node.value <= add.value) {
            if (node.right == null) {
                node.right = add;
                return;
            }
            //右子树遍历
            add(add, node.right);
        }
        if (node.value > add.value) {
            if (node.left == null) {
                node.left = add;
                return;
            }
            //左子树遍历
            add(add, node.left);
        }
    }

    public void del(int value) {
        if (root == null) {
            return;
        }
        Node node = searchNode(value);
        if(node == null){
            return;
        }
        Node parentNode = searchParentNode(value);
        if (parentNode == null) {
            root = null;
            return;
        }
        //第一种情况删除子节点
        if (node.left == null && node.right == null) {
            if (parentNode.left == node) {
                parentNode.left = null;
            } else {
                parentNode.right = null;
            }
            return;
        }
        //第二种情况删除单一子节点
        if ((node.left == null && node.right != null) || (node.left != null && node.right == null)) {
            if (node.left != null) {
                //左子节点不为空
                if (parentNode.left == node) {
                    parentNode.left = node.left;
                } else {
                    parentNode.right = node.left;
                }
            } else {
                //右子节点不为空
                if (parentNode.left == node) {
                    parentNode.left = node.right;
                } else {
                    parentNode.right = node.right;
                }
            }
            return;
        }
        //第三种情况删除双子节点
        //从targetNode的右子树找到最小的节点
        Node temp = node.right;
        while (temp.left != null || temp.right != null) {
            if (temp.left != null && temp.value > temp.left.value) {
                temp = temp.left;
            }
            if (temp.right != null && temp.value > temp.right.value) {
                temp = temp.right;
            }
        }
        Node node1 = searchParentNode(temp.value);
        //删除最小节点
        if (node1.left == temp) {
            node1.left = null;
        } else {
            node1.right = null;
        }
        //node和temp互换
        if (parentNode.left == node) {
            parentNode.left = temp;
        } else {
            parentNode.right = temp;
        }
        temp.left = node.left;
        temp.right = node.right;
    }

    public void preOrder() {
        if (root != null) {
            root.preOrder();
        }
    }

    public Node searchParentNode(int value) {
        if (root != null) {
            Node parent = null;
            if (root.value == value) {
                return parent;
            } else if (root.left != null && value < root.value) {
                return searchParentNode(value, root.left, root);
            } else if (root.right != null && value > root.value) {
                return searchParentNode(value, root.right, root);
            }
        }
        return null;
    }

    private Node searchParentNode(int value, Node node, Node parent) {
        if (value == node.value) {
            return parent;
        } else if (value < node.value && node.left != null) {
            return searchParentNode(value, node.left, node);
        } else if (value > node.value && node.right != null) {
            return searchParentNode(value, node.right, node);
        }
        return null;
    }

    public Node searchNode(int value) {
        if (root != null) {
            if (root.value == value) {
                return root;
            } else if (root.left != null && value < root.value) {
                return searchNode(value, root.left);
            } else if (root.right != null && value > root.value) {
                return searchNode(value, root.right);
            }
        }
        return null;
    }

    private Node searchNode(int value, Node node) {
        if (value == node.value) {
            return node;
        } else if (value < node.value && node.left != null) {
            return searchNode(value, node.left);
        } else if (value > node.value && node.right != null) {
            return searchNode(value, node.right);
        }
        return null;
    }
}

class Node {
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    public void preOrder() {
        System.out.println(this);
        if (left != null) {
            left.preOrder();
        }
        if (right != null) {
            right.preOrder();
        }
    }
}
