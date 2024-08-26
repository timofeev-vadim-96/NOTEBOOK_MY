
/**
 * 83. Remove Duplicates from Sorted List
 */
public class RemoveDuplicatesFromSortedLinkedList {
    public static void main(String[] args) {
//        ListNode node = new ListNode(0, new ListNode(1, new ListNode(2, new ListNode(2, new ListNode(3)))));
        ListNode node = new ListNode(1, new ListNode(1, new ListNode(1)));
        deleteDuplicates(node);
        while (node != null) {
            System.out.println(node.val + " ");
            node = node.next;
        }
    }

    //решение
    public static ListNode deleteDuplicates(ListNode head) {
        ListNode realHead = head;

        if (head == null || head.next == null) return head;
        ListNode previous = head;
        head = head.next;
        while (head != null) {
            if (head.val == previous.val) {
                previous.next = head.next;
                head = head.next;
            } else {
                previous = head;
                head = head.next;
            }
        }

        return realHead;
    }

    private static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}