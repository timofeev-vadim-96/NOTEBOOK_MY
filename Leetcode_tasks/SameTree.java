
/**
 * 100. Same Tree
 * 
 * Учитывая корни двух двоичных деревьев p и q, напишите функцию, чтобы проверить, совпадают ли они или нет.
 * Два бинарных дерева считаются одинаковыми, если они структурно идентичны, а узлы имеют одинаковое значение.
 */
public class SameTree {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null) {
            return false;
        }
        if (p.val != q.val) {
            return false;
        }

        //если значения нод не null и они равны
        return isSameTree(p.left, q.left) &&
                isSameTree(p.right, q.right);
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
