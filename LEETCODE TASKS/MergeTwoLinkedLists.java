/**
 * 21. Merge Two Sorted Lists
 * Замерджить два отсортированных связных списка (входящие их heads) и вернуть head итогового списка.
*/
public class MergeTwoLinkedLists {
    ListNode head; //изначально их нет, ввел для удобства из определения связных списков
    ListNode tail;

    private class ListNode { //определение класса Ноды из задания
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

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (head == null) {
            if (list1 != null && list2 != null) {
                if (list1.val >= list2.val) {
                    head = list2;
                    tail = list2;
                    list2 = list2.next;
                } else {
                    head = list1;
                    tail = list1;
                    list1 = list1.next;
                }
            } else if (list1 != null) {
                head = list1;
                tail = list1;
                list1 = list1.next;
            } else if (list2 != null) {
                head = list2;
                tail = list2;
                list2 = list2.next;
            }
        }

        while (list1 != null && list2 != null) {
            if (list1.val >= list2.val) {
                tail.next = list2;
                tail = list2;
                list2 = list2.next;
            } else {
                tail.next = list1;
                tail = list1;
                list1 = list1.next;
            }
        }
        while (list1 != null) { //если в первом списке остались ноды
            tail.next = list1;
            tail = list1;
            list1 = list1.next;
        }
        while (list2 != null) { //если во втором списке остались ноды
            tail.next = list2;
            tail = list2;
            list2 = list2.next;
        }
        return head;
    }
}
