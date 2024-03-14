
/**
 * 101. Symmetric Tree
 * Дан корень дерева. Определить является ли оно симметричным относительно корня
 */
class SymmetricTree {
    /**
     * Определение ноды для дерева
     */
    public class TreeNode { 
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

    //Решение
    
    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true; //если корень пуст - то логично, что дерево симметрично
        else {
            return isSymmetric(root.left, root.right);
        }
    }

    public boolean isSymmetric (TreeNode left, TreeNode right){
        if (left == null && right == null) return true; //если оба узла null, значит дерево симметрично
        if (left == null || right == null) return false; //если одна из веток пуста, значит дерево не симметрично
        //если значения правого и левого равны + если левый ребенок левого звена == правому ребенку правого звена
        //а также правый ребенок левого звена == левому ребенку правого звена
        return left.val == right.val && isSymmetric(left.left, right.right) && isSymmetric(left.right, right.left);
    }

    
}