package com.xz.avltree;

/**
 * @Package: com.xz.avltree
 * @ClassName: Demo
 * @Author: xz
 * @Date: 2020/6/10 14:00
 * @Version: 1.0
 */
public class Demo {

    public static void main(String[] args) {
        int[] arr = {4,3,7,6,8,5};
        AVLTree avlTree = new AVLTree();
        for (int i : arr) {
            avlTree.add(new Node(i));
        }
        avlTree.preOrder();
        System.out.println(avlTree.height());
    }
}
class AVLTree{
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
        //左子树高度减右子树高度
        int heightDiff = (root.left==null?0:root.left.height())-(root.right==null?0:root.right.height());
        if(heightDiff < -1){
            if(root.right != null && root.right.left != null && root.right.right != null
                    && root.right.left.height() > root.right.right.height()){
                //双旋转
                //右旋转
                //新节点
                Node newNode = new Node(root.right.value);
                newNode.right = root.right.right;
                newNode.left = root.right.left==null?null:root.right.left.right;
                Node temp = root.right.left==null?null:root.right.left.left;
                root.right = root.right.left;
                root.right.right = newNode;
                root.right.left = temp;
            }
            //左旋转
            //新节点
            Node newNode = new Node(root.value);
            //新节点的左节点等于root的左节点
            newNode.left = root.left;
            //新节点的右节点等于root的右节点的左节点
            newNode.right = root.right==null?null:root.right.left;
            Node temp = root.right==null?null:root.right.right;
            //root为root的右节点
            root = root.right;
            //新root的右节点为root的右节点的右节点
            root.right = temp;
            //新root的左节点为新节点
            root.left = newNode;
        }else if(heightDiff > 1){
            if(root.left != null && root.left.left != null && root.left.right != null
                    && root.left.left.height() < root.left.right.height()){
                //双旋转
                //左旋转
                //新节点
                Node newNode = new Node(root.left.value);
                //新节点的左节点等于root的左节点
                newNode.left = root.left.left;
                //新节点的右节点等于root的右节点的左节点
                newNode.right = root.left.right==null?null:root.left.right.left;
                Node temp = root.left.right==null?null:root.left.right.right;
                //root为root的右节点
                root.left = root.left.right;
                //新root的右节点为root的右节点的右节点
                root.left.right = temp;
                //新root的左节点为新节点
                root.left.left = newNode;
            }
            //右旋转
            //新节点
            Node newNode = new Node(root.value);
            newNode.right = root.right;
            newNode.left = root.left==null?null:root.left.right;
            Node temp = root.left==null?null:root.left.left;
            root = root.left;
            root.right = newNode;
            root.left = temp;
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

    public int height(){
        if(root == null){
            return 0;
        }
        return root.height();
    }
}
class Node {
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }

    public int height(){
        return Math.max(left==null?0:left.height(),right==null?0:right.height())+1;
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
