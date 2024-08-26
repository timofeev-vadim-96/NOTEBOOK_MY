
/**
 * 112. Path Sum
 *
 * Учитывая root двоичное дерево и целое число targetSum, верните true, если у дерева есть путь от корня к листу, такой,
 * что суммирование всех значений вдоль пути равно targetSum.
 * <p>
 * Лист - это узел, не имеющий дочерних элементов.
 */
public class PathSum {
    public static void main(String[] args) {
        //22
//        TreeNode node = new TreeNode(5, new TreeNode(4, new TreeNode(11, new TreeNode(7), new TreeNode(2)), null), new TreeNode(8, new TreeNode(13), new TreeNode(4, null, new TreeNode(1))));
        //-5
        TreeNode node = new TreeNode(-2, null, new TreeNode(-3));
        //-1
//        TreeNode node = new TreeNode(1, new TreeNode(-2, new TreeNode(1, new TreeNode(-1), null), new TreeNode(3)), new TreeNode(-3, new TreeNode(-2), null));
        boolean b = hasPathSum(node, -5);
        System.out.println(b);
    }

    public static boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }
        System.out.println("root value: " + root.val + ", target sum: " + targetSum);
        //это лист дерева или нет (нода без детей) + искомая сумма
        if (root.val == targetSum && (root.left == null && root.right == null)) {
            return true;
        }
        return hasPathSum(root.left, targetSum - root.val) || 
                hasPathSum(root.right, targetSum - root.val);
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