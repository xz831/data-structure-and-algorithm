package com.xz.binarytree;

/**
 * @Package: com.xz.binarytree
 * @ClassName: Demo
 * @Author: xz
 * @Date: 2020/6/1 11:40
 * @Version: 1.0
 */
public class Demo {

    public static void main(String[] args) {
        BinaryTree binaryTree = new BinaryTree();
        Node node1 = new Node(1, "宋江");
        Node node2 = new Node(2, "卢俊义");
        Node node3 = new Node(3, "吴用");
        Node node4 = new Node(4, "林冲");
        Node node5 = new Node(5, "关胜");
        node1.left = node2;
        node1.right = node3;
        node3.right = node4;
        node3.left = node5;
        binaryTree.setRoot(node1);
        //前序遍历
        System.out.println("前序遍历");
        binaryTree.preOrder();
        //中序遍历
        System.out.println("中序遍历");
        binaryTree.midOrder();
        //后序遍历
        System.out.println("后序遍历");
        binaryTree.postOrder();
        //前序查找
        System.out.println("前序查找");
        System.out.println(binaryTree.preSearch(5));
        //中序查找
        System.out.println("中序查找");
        System.out.println(binaryTree.midSearch(5));
        //后序查找
        System.out.println("后序查找");
        System.out.println(binaryTree.postSearch(5));
        binaryTree.remove(1);
        binaryTree.preOrder();
    }
}
class BinaryTree{
    private Node root;

    public void setRoot(Node root) {
        this.root = root;
    }

    public void preOrder(){
        if(root != null){
            root.preOrder();
        }
    }
    public void midOrder(){
        if(root != null){
            root.midOrder();
        }
    }
    public void postOrder(){
        if(root != null){
            root.postOrder();
        }
    }
    public Node preSearch(int id){
        if(root != null){
            return root.preSearch(id);
        }
        return null;
    }
    public Node midSearch(int id){
        if(root != null){
            return root.midSearch(id);
        }
        return null;
    }
    public Node postSearch(int id){
        if(root != null){
            return root.postSearch(id);
        }
        return null;
    }
    public boolean remove(int id){
        if(root != null){
            return root.remove(id);
        }
        return false;
    }
}
class Node{
    public int id;
    public String name;
    public Node left;
    public Node right;

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

    /**
     * 前序遍历
     */
    public void preOrder(){
        //父节点
        System.out.println(this);
        //左子树遍历
        if(left != null){
            left.preOrder();
        }
        //右子树遍历
        if(right != null){
            right.preOrder();
        }
    }

    /**
     * 前序查找
     */
    public Node preSearch(int id){
        if(this.id == id){
            return this;
        }
        if(left != null){
            Node temp = left.preSearch(id);
            if(temp != null){
                return temp;
            }
        }
        if(right != null){
            Node temp = right.preSearch(id);
            if(temp != null){
                return temp;
            }
        }
        return null;
    }

    /**
     * 中序遍历
     */
    public void midOrder(){
        if(left != null){
            left.midOrder();
        }
        System.out.println(this);
        if(right != null){
            right.midOrder();
        }
    }

    /**
     * 中序查找
     */
    public Node midSearch(int id){
        if(left != null){
            Node temp = left.midSearch(id);
            if(temp != null){
                return temp;
            }
        }
        if(this.id == id){
            return this;
        }
        if(right != null){
            Node temp = right.midSearch(id);
            if(temp != null){
                return temp;
            }
        }
        return null;
    }

    /**
     * 后序遍历
     */
    public void postOrder(){
        if(left != null){
            left.postOrder();
        }
        if(right != null){
            right.postOrder();
        }
        System.out.println(this);
    }

    /**
     * 后序查找
     */
    public Node postSearch(int id){
        if(left != null){
            Node temp = left.postSearch(id);
            if(temp != null){
                return temp;
            }
        }
        if(right != null){
            Node temp = right.postSearch(id);
            if(temp != null){
                return temp;
            }
        }
        if(this.id == id){
            return this;
        }
        return null;
    }

    /**
     * 删除
     */
    public boolean remove(int id){
        if(this.id == id){
            System.out.println("无法删除根节点");
            return false;
        }
        if(left != null){
            if(left.id == id){
                left = null;
                return true;
            }else{
                boolean remove = left.remove(id);
                if(remove){
                    return true;
                }
            }
        }
        if(right != null){
            if(right.id == id){
                right = null;
                return true;
            }else{
                return right.remove(id);
            }
        }
        return false;
    }
}
