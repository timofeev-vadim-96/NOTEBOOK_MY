
import java.util.HashSet;
import java.util.Set;

/**
 * 160. Intersection Of Two Linked Lists
 * Given the heads of two singly linked-lists headA and headB, return the node at which the two lists intersect. 
 * If the two linked lists have no intersection at all, return null
 */
public class IntersectionOfTwoLinkedLists {
    public static void main(String[] args) {

    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        Set<ListNode> uniqueNumbers = new HashSet<>();
        while (headA != null) {
            uniqueNumbers.add(headA);
            headA = headA.next;
        }
        int temporarySize = uniqueNumbers.size();
        while (headB != null) {
            uniqueNumbers.add(headB);
            if (temporarySize == uniqueNumbers.size()) {
                return headB;
            } else {
                temporarySize = uniqueNumbers.size();
                headB = headB.next;
            }
        }

        return null;
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }
}