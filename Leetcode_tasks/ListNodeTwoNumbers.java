/**
 * 2. Add Two Numbers (Middle)
 * Дано два входящих односвязных списка, представляющих собой интовые значения в обратном порядке "1-2-3". 
 * Вернуть развернутый в обратном порядке список. Типо 1-2-3 и 2-3-4, вернуть 321+432 = 753 = 3-5-7
 */
import java.math.BigInteger;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.stream.Collectors;

public class ListNodeTwoNumbers {

    public class ListNode {
        public int val;
        public ListNode next;
        public ListNode() {}
        public ListNode(int val) { this.val = val; }
        public ListNode(int val, ListNode next) { this.val = val; this.next = next; }
   }
   
    ListNode head;

    public ListNode addTwoNumbers3(ListNode l1, ListNode l2) {
        StringBuilder s1 = new StringBuilder();
        StringBuilder s2 = new StringBuilder();
        do {
            s1.append(l1.val);
            l1 = l1.next;
        } while ((l1 != null));
        do {
            s2.append(l2.val);
            l2 = l2.next;
        } while ((l2 != null));
        StringBuilder s3 = new StringBuilder(String.valueOf(new BigInteger(s1.reverse().toString()).add(new BigInteger(s2.reverse().toString()))));
        char [] resArr = s3.reverse().toString().toCharArray();
        ListNode l3 = new ListNode();
        for (int i = 0; i < resArr.length; i++) {
            if (head == null) {
                head = new ListNode(Character.getNumericValue(resArr[i]));
                if (i != resArr.length - 1) {
                    head.next = l3;
                }
            } else {
                l3.val = Character.getNumericValue(resArr[i]);
                if (i != resArr.length - 1) {
                    l3.next = new ListNode();
                    l3 = l3.next;
                }
            }
        }
        return head;
    }

    public void printListNode(ListNode ln) {
        while (ln != null) {
            System.out.print(ln.val + " ");
            ln = ln.next;
        }
    }
}

