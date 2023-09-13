
public class LinkedList1 {
    Node head;

    public LinkedList1 add(int inputNode){ //метод push in Java
        Node newNode = new Node();
        newNode.value = inputNode;
        if (head == null) head = newNode;
        else{
            newNode.next = head;
            head = newNode;
        }
        return this;
    }

    public Integer pop(){
        Integer result = null;
        if (head != null) {
            result = head.value;
            head = head.next;
        }
        return result;
    }

    public void revert(Node currentNode, Node previousNode){ //для разворота связного списка
        if (currentNode.next == null) head = currentNode;
        else {
            revert (currentNode.next, currentNode);
        }
        currentNode.next = previousNode;
    }

    public void revert(){
        if (head != null && head.next != null){
            Node temp = head;
            revert(head.next, head);
            temp.next = null;
        }
    }


    private class Node{
        int value;
        Node next;

        @Override
        public String toString() {
            return String.format("%d", value);
        }
    }

    public void print (){
        Node currentNode = head;
        while (currentNode != null){
            System.out.printf(currentNode + " ");
            currentNode = currentNode.next;
        }
        System.out.println();
    }
}
