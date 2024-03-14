/**
 * 234. Palindrome Linked List. Учитывая head односвязный список, верните значение true, если это палиндром, иначе false
 */
public class PalindromeLinkedList {
    public class ListNode {
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

    public boolean isPalindrome(ListNode head) {
        ListNode copiedHead = head;
        if (copiedHead == null) return true;
        int counter = 0;
        do {
            counter++;
            copiedHead = copiedHead.next;
        } while (copiedHead != null);

        if (counter == 1) return true;

        int[] leftPartOfList = new int[counter / 2];

        for (int i = 0; i < leftPartOfList.length; i++) {
            leftPartOfList[i] = head.val;
            head = head.next;
        }

        boolean anOddNumberOfElementsInTheList = counter % 2 != 0; //пропустить средний элемент, если нечетное

        if (anOddNumberOfElementsInTheList) head = head.next;

        for (int i = leftPartOfList.length - 1; i >= 0; i--) {
            if (leftPartOfList[i] != head.val) return false;
            head = head.next;
        }
        return true;
    }
}
