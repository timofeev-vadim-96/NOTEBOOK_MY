
/**
 * 19. Remove Nth Node From End of List
 *
 * Учитывая head связанный список, удалите nth узел из конца списка и верните его начало.
 */

class RemoveNodeFromEndOfLinkedList {

    public static void main(String[] args) {
        ListNode node = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
//        ListNode node = new ListNode(1, new ListNode(2));

        ListNode listNode = removeNthFromEnd(node, 5);
        while (listNode != null){
            System.out.print(listNode.val + " ");
            listNode = listNode.next;
        }
    }

    public static ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null || (head.next == null && n == 1)) return null;

        int length = countLength(head);

        if (length == n) {
            return head.next;
        }

        int nodeToRemove = length -1 - n;

        ListNode previousNode = head;
        ListNode node = head.next;
        int currentNodeCounter = 0;
        while (node != null) {
            if (currentNodeCounter == nodeToRemove){
                previousNode.next = node.next;
                return head;
            }
            else {
                previousNode = node;
                node = node.next;
                currentNodeCounter++;
            }
        }
        return head;
    }

    private static int countLength (ListNode head){
        ListNode node = head;
        if (node == null) return 0;
        int counter = 0;
        while (node != null){
            node = node.next;
            counter++;
        }
        return counter;
    }
}

