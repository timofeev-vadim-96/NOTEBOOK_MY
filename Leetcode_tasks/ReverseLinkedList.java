
/**
 * 206. Reverse Linked List
 * развернуть односвязный список, вернул новую head
 */
public class ReverseLinkedList {
    ListNode innerHead;
    ListNode previous;
    public ListNode reverseList(ListNode head) {
        if (head == null) return head;
        else innerHead = new ListNode(head.val); //новая head
        while (head.next != null){
            head = head.next; //сразу смещаем, чтобы работать относительно нового звена
            previous = new ListNode(innerHead.val, previous); //сохраняем текущее звено как прошлое
            innerHead = new ListNode(head.val, previous); //задаем новую head на основе текущего
            // элемента со ссылкой на прошлое звено
        }
        return this.innerHead;
    }

/**
 * Definition for singly-linked list.
 */
public class ListNode {
    int val;
    public ListNode next;

    public ListNode() {
    }

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    public int getVal() {
        return val;
    }
}
}
