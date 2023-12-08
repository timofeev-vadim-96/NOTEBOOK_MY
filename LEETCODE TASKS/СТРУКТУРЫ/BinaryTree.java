package СТРУКТУРЫ;

public class BinaryTree {
    Node root;

    enum Color{
        RED, BLACK;
    }
    class Node{
        int value;
        //ссылки на детей
        Node left;
        Node right;
        Color color; //параметр цевета для балансировки
    }
//    public boolean find(int value){ // с помощью цикла
//        if (root == null) return false;
//        Node currentNode = root;
//        while (currentNode != null){
//            if (currentNode.value == value) return true;
//            else if (currentNode.value > value){
//               currentNode = currentNode.left;
//            }
//            else {
//                currentNode = currentNode.right;
//            }
//        }
//        return false;
//    }
    public boolean insert(int value){
        if (root == null){
            root = new Node();
            root.value = value;
            root.color = Color.BLACK;
            return true;
        }
        boolean result = insert(root, value);
        root = rebalance(root);
        root.color = Color.BLACK;
        return result;
    }

    public boolean insert (Node node, int value){
        if (node.value == value) return false;
        if (node.value > value) {
            if (node.left == null){
                node.left = new Node();
                node.left.value = value;
                node.left.color = Color.RED;
                return true;
            }
            boolean result = insert(node.left, value);
            node.left = rebalance(node.left);
            return result;
        }
        else {
            if (node.right == null){
                node.right = new Node();
                node.right.value = value;
                node.right.color = Color.RED;
                return true;
            }
            boolean result = insert(node.right, value);
            node.right = rebalance(node.right);
            return result;
        }
    }

    public boolean find (int value){
        return find (this.root, value);
    }
    public boolean find (Node node, int value){
        if (node == null) return false;
        if (node.value == value) return true;
        if (node.value > value) return find (node.left, value);
        else return find (node.right, value);
    }

    private Node rebalance(Node node) {
        Node result = node;
        boolean needRebalance;
        do {
            needRebalance = false;
            if (result.right != null && result.right.color == Color.RED
                    && (result.left == null || result.left.color == Color.BLACK)) {
                needRebalance = true;
                result = rightSwap(result);
            }
            if (result.left != null && result.left.color == Color.RED
                    && result.left.left != null && result.left.left.color == Color.RED) {
                needRebalance = true;
                result = leftSwap(result);
            }
            if (result.left != null && result.left.color == Color.RED
                    && result.right != null && result.right.color == Color.RED) {
                needRebalance = true;
                colorSwap(result);
            }
        } while (needRebalance);
        return result;
    }

    private void colorSwap(Node node) {
        node.right.color = Color.BLACK;
        node.left.color = Color.BLACK;
        node.color = Color.RED;
    }

    private Node leftSwap(Node node) {
        Node leftChild = node.left;
        Node between = leftChild.right;
        leftChild.right = node;
        node.left = between;
        leftChild.color = node.color;
        node.color = Color.RED;
        return leftChild;
    }

    private Node rightSwap(Node node) {
        Node rightChild = node.right;
        Node between = rightChild.left;
        rightChild.left = node;
        node.right = between;
        rightChild.color = node.color;
        node.color = Color.RED;
        return rightChild;
    }
}

