/**
 * 111. Минимальная глубина бинарного дерева
 *
 * Учитывая root размер двоичного дерева, верните его минимальную глубину.
 * Минимальная глубина бинарного дерева - это количество узлов на самом коротком пути от корневого узла до самого
 * ближнего конечного узла.
 */
public class MinDepthOfBinaryTree {
    public static void main(String[] args) {
        //мин. глубина = 1
//        TreeNode node = new TreeNode(-2, null, new TreeNode(-3));
        //мин. глубина = 5
//        TreeNode node = new TreeNode(2, null, new TreeNode(3, null, new TreeNode(4, null, new TreeNode(5, null, new TreeNode(6)))));
        //мин. глубина = 2
        TreeNode node = new TreeNode(3, new TreeNode(9), new TreeNode(20, new TreeNode(15), new TreeNode(7)));
        System.out.println(minDepth(node));
    }

    public static int minDepth(TreeNode root) {
        if (root == null) return 0;

        int leftMinDepth = 1;
        if (root.left != null) {
            leftMinDepth += minDepth(root.left);
        }

        int rightMinDepth = 1;
        if (root.right != null) {
            rightMinDepth += minDepth(root.right);
        }

        if (root.left != null && root.right != null) {
            return Math.min(leftMinDepth, rightMinDepth);
        }

        if (root.left != null) {
            return leftMinDepth;
        }

        if (root.right != null) {
            return rightMinDepth;
        }

        return 1;
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
