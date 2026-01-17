/**
 * 203. Remove Linked List Elements
 * 
 * Given the head of a linked list and an integer val, remove all the nodes of the linked list that has Node.val == val, and return the new head.
 */
public class RemoveLinkedListElements {
    public static void main(String[] args) {
        //test case 1
        //head = [1,2,6,3,4,5,6], val = 6
//        ListNode node = new ListNode(1, new ListNode(2, new ListNode(6, new ListNode(3, new ListNode(4, new ListNode(5, new ListNode(6)))))));
        //test case 2
//        ListNode node = new ListNode(7, new ListNode(7, new ListNode(7, new ListNode(7))));
        //test case 3
        ListNode node = null;
        ListNode listNode = removeElements(node, 6);
        while (listNode != null) {
            System.out.print(listNode.val + " ");
            listNode = listNode.next;
        }
    }

    public static ListNode removeElements(ListNode head, int val) {
        ListNode trueHead = head;
        ListNode previous = null;

        while (head != null) {
            if (head.val == val) {
                if (previous == null) {
                    trueHead = head.next;
                } else {
                    previous.next = head.next;
                }
            } else {
                previous = head;
            }

            head = head.next;
        }

        return trueHead;
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