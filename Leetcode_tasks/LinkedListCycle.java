import java.util.HashSet;
import java.util.Set;

/**
 * 141. Linked List Cycle
 *
 * Учитывая head заголовок связанного списка, определите, есть ли в связанном списке цикл.
 */
public class LinkedListCycle {
    public static void main(String[] args) {
        ListNode node1 = new ListNode(3);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;

        System.out.println(hasCycle(node1));
    }

    public static boolean hasCycle(ListNode head) {
        Set<ListNode> set = new HashSet<>();
        while (head.next != null) {
            if (set.contains(head)) {
                return true;
            }
            set.add(head);

            head = head.next;
        }

        return false;
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }
}
