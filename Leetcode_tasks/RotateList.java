
/**
 * 61. Rotate List 
 * 
 * Given the head of a linked list, rotate the list to the right by k places.
 * 
 * Input: head = [1,2,3,4,5], k = 2
 * Output: [4,5,1,2,3]
 */
public class RotateList {
    private static ListNode generalHead;

    public static void main(String[] args) {
        var node = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        ListNode result = rotateRight(node, 24);
        while (result != null) {
            System.out.print(result.val + " ");
            result = result.next;
        }
    }

    public static ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null) {
            return head;
        }

        int size = calculateSize(head);
        //22 - 22/5*5
        int offset = k - k / size * size;

        generalHead = head;

        ListNode previous;
        int index = 0;
        while (index < offset) {
            previous = head;
            head = head.next;
            if (head.next == null) {
                previous.next = null;
                head.next = generalHead;
                generalHead = head;
                index++;
            }
        }

        return head;
    }

    private static int calculateSize(ListNode head) {
        int size = 0;
        while (head != null) {
            size++;
            head = head.next;
        }
        return size;
    }

    public static class ListNode {
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
