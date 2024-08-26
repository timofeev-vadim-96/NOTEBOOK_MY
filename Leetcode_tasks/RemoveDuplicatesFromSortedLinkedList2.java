
import java.util.Map;
import java.util.TreeMap;

/**
 * 82. Remove Duplicates from Sorted List 2 (middle)
 * 
 * плохое решение (beats 5%)
 */
public class RemoveDuplicatesFromSortedLinkedList2 {
    public static void main(String[] args) {
//        ListNode node = new ListNode(0, new ListNode(1, new ListNode(2, new ListNode(2, new ListNode(3)))));
        ListNode node = new ListNode(1, new ListNode(1, new ListNode(1)));
        ListNode result = deleteDuplicates(node);
        while (result != null) {
            System.out.println(result.val + " ");
            result = result.next;
        }
    }

    //решение
    public static ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) return head;

        Map<Integer, Integer> map = new TreeMap<>();

        while (head != null) {
            if (map.get(head.val) == null) {
                map.put(head.val, 1);
            } else {
                int value = map.get(head.val);
                map.put(head.val, ++value);
            }
            head = head.next;
        }

        ListNode result = null;

        //для ретерна
        ListNode realHead = result;
        ListNode previous = null;
        boolean isRealHeadHasBeenInit = false;

        for (Integer key : map.keySet()) {
            if (map.get(key) == 1) {
                result = new ListNode(key);
                if (previous != null) {
                    previous.next = result;
                }

                if (!isRealHeadHasBeenInit) {
                    isRealHeadHasBeenInit = true;
                    realHead = result;
                    previous = result;
                }

                previous = result;
                result = result.next;
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
