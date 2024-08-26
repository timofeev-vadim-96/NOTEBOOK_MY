/**
 * 104. Максимальная глубина бинарного дерева
 *
 * Учитывая root размер двоичного дерева, верните его максимальную глубину.
 * Максимальная глубина бинарного дерева - это количество узлов на самом длинном пути от корневого узла до самого
 * дальнего конечного узла.
 */
public class MaxDepthOfBinaryTree {
    public static void main(String[] args) {
        //макс. глубина = 2
        TreeNode node = new TreeNode(-2, null, new TreeNode(-3));
        System.out.println(maxDepth(node));
    }

    public static int maxDepth(TreeNode root) {
        if (root == null) return 0;

        int leftMaxDepth = 1;
        if (root.left != null) {
            leftMaxDepth += maxDepth(root.left);
        }

        int rightDepth = 1;
        if (root.right != null) {
            rightDepth += maxDepth(root.right);
        }

        return Math.max(leftMaxDepth, rightDepth);
    }


    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}

