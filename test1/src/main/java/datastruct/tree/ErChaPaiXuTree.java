package datastruct.tree;

/**
 * 二叉排序树
 */
public class ErChaPaiXuTree {
    Node root;

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public static void main(String[] args) {
        ErChaPaiXuTree tree = new ErChaPaiXuTree();
        tree.init();
        tree.printInfix();
        System.out.println();

//        tree.delValue(7);
//        tree.printInfix();
        System.out.println();
        tree.delValue(10);

        tree.printInfix();

        System.out.println();
    }


    public void init() {
        int[] array = new int[]{7, 3, 10, 12, 5, 1, 9, 2};
        //int[] array = new int[]{7,6,8};
        for (int i = 0; i < array.length; i++) {
            addNode(array[i]);
        }
    }


    public Node findNode(int value) {
        if (root == null) {
            return null;
        }
        if (root.getValue() == value) {
            return root;
        }
        return findNode(root, value);
    }

    //查找节点
    public Node findNode(Node node, int value) {
        Node no = null;
        if (node.getLeft() != null) {
            if (node.getLeft().getValue() == value) {
                no = node.getLeft();
                return no;
            }
            no = findNode(node.getLeft(), value);
        }
        if (no != null) {
            return no;
        }
        if (node.getRight() != null) {
            if (node.getRight().getValue() == value) {
                no = node.getRight();
                return no;
            }
            no = findNode(node.getRight(), value);
        }
        return no;
    }


    public Node findParent(int value) {
        if (root == null) {
            return null;
        }
        if (root.getValue() == value) {
            return null;
        }
        return findParent(root, value);
    }

    //早到当前节点的父节点
    public Node findParent(Node node, int value) {
        Node no = null;
        if (node.getLeft() != null) {
            if (node.getLeft().getValue() == value) {
                no = node;
                return no;
            }
            no = findParent(node.getLeft(), value);
        }
        if (no != null) {
            return no;
        }
        if (node.getRight() != null) {
            if (node.getRight().getValue() == value) {
                no = node;
                return no;
            }
            no = findParent(node.getRight(), value);
        }
        return no;
    }

    //查找最小值并且删除
    private int findNodeMinValueAndDelete(Node node) {
        while (node.getLeft() != null) {
            node = node.getLeft();
        }
        //因为最小的值只有叶子节点或者一个节点带一个子节点
        delValue(node.getValue());
        return node.getValue();
    }


    public void delValue(int value) {
        Node node = findNode(value);
        if (node == null) {
            return;
        }
        Node parent = findParent(value);
        //删除叶子节点
        if (node.getLeft() == null && node.getRight() == null) {
            if (parent == null) {
                root = null;
                return;
            }
            if (parent.getLeft() != null && parent.getLeft().getValue() == value) {
                parent.setLeft(null);
                return;
            }
            if (parent.getRight() != null && parent.getRight().getValue() == value) {
                parent.setRight(null);
                return;
            }
        }
        //删除1个叶子节点的节点
        if (node.getLeft() != null && node.getRight() == null || node.getLeft() == null && node.getRight() != null) {
            if (parent == null) {
                if (node.getLeft() != null) {
                    root = node.getLeft();
                    return;
                }
                if (node.getRight() != null) {
                    root = node.getRight();
                    return;
                }
            }
            if (node.getLeft() != null) {
                if (parent.getLeft().getValue() == value) {
                    parent.setLeft(node.getLeft());
                    return;
                }
                if (parent.getRight().getValue() == value) {
                    parent.setRight(node.getLeft());
                    return;
                }
            }
            if (node.getRight() != null) {
                if (parent.getLeft().getValue() == value) {
                    parent.setLeft(node.getRight());
                    return;
                }
                if (parent.getRight().getValue() == value) {
                    parent.setRight(node.getRight());
                    return;
                }
            }
        }
        //删除2个叶子节点的节点
        if (node.getRight() != null && node.getLeft() != null) {
            int minValue = findNodeMinValueAndDelete(node.getRight());
            node.setValue(minValue);
        }
    }


    public void addNode(int value) {
        if (root == null) {
            root = new Node(value);
            return;
        }
        addNode(root, value);
    }

    public void addNode(Node node, int value) {
        if (value < node.getValue()) {
            if (node.getLeft() == null) {
                node.setLeft(new Node(value));
                return;
            }
            addNode(node.getLeft(), value);
        } else {
            if (node.getRight() == null) {
                node.setRight(new Node(value));
                return;
            }
            addNode(node.getRight(), value);
        }

    }

    public void printInfix() {
        if (root == null) {
            System.out.println("当前没有元素");
            return;
        }
        printInfix(root);
    }

    public void printInfix(Node node) {
        if (node == null) {
            return;
        }
        printInfix(node.getLeft());
        System.out.print(node.getValue() + " ");
        printInfix(node.getRight());
    }


    private class Node {
        private int value;
        private Node left;
        private Node right;

        public Node(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public Node getLeft() {
            return left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(Node right) {
            this.right = right;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    '}';
        }
    }
}
