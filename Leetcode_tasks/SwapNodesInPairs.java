
public class SwapNodesInPairs {
    public static void main(String[] args) {
//        ListNode node = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4))));
        ListNode node = new ListNode(1, new ListNode(2, new ListNode(3)));
//        ListNode node = new ListNode(1);

        ListNode result = swapPairs(node);
        while (result != null) {
            System.out.print(result.val + " ");
            result = result.next;
        }
    }

    public static ListNode swapPairs(ListNode head) {
        ListNode res = head;
        if (head == null) {
            return head;
        } else {
            ListNode previous = head;
            head = head.next;

            while (head != null) {
                int temp = previous.val;
                previous.val = head.val;
                head.val = temp;

                if (head.next == null) {
                    return res;
                }
                previous = head.next;
                head = head.next.next;
            }
        }

        return res;
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