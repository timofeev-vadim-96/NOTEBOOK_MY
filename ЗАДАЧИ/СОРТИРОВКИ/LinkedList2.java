//Двусвязный список
public class LinkedList2 {
    Node head;
    Node tail;

    public LinkedList2 add(int value){ //функция добавления ноды в конец списка
        Node node = new Node();
        node.value = value;
        if (head == null){
            head = node;
            tail = node;
        }
        else {
            tail.next = node;
            node.previous = tail;
            tail = node;
        }
        return this;
    }

    public void delete (Node node){
        if (tail == node) {
            tail = node.previous;
            node.previous.next = null;
        } else node.previous.next = node.next;
        if (head == node) {
            head = node.next;
            node.next.previous = null;
        } else node.next.previous = node.previous;
    }

    public void revert(){ //для разворота связного списка
        if (head == null) return;
        Node temp = tail;
        tail = head;
        head = temp;
        Node currentNode = head;
        do {
            temp = currentNode.previous;
            currentNode.previous = currentNode.next;
            currentNode.next = temp;
            currentNode = currentNode.next;
        } while (currentNode != null);
    }

    //не рассматривается вариант с пустым списком, т.к. есть ссылка на одну из нод
    public void add (int value, Node node){ //функция добавления ноды после конкретной ноды
        Node newNode = new Node();
        newNode.value = value;
        newNode.previous = node;
        if (node.next != null){ //если входная нода - не tail
            node.next.previous = newNode; //тогда новая нода - предыдущая для следующей после входящей
            newNode.next = node.next; //следующая нода после входящей теперь следующая после новой
        }
        else tail = newNode; //входящая нода была хвостом, теперь новая нода - хвост
        node.next = newNode;
    }

    public Integer pick(){ //вернуть последний элемент и удалить его из очереди
        Integer result = null;
        if (tail != null) {
            result = tail.value;
            tail.previous.next = null;
            tail = tail.previous;
        }
        return result;
    }

    public Node find (int value){
        Node currantNode = head;
        while (currantNode != null){
            if (currantNode.value == value) return currantNode;
            else currantNode = currantNode.next;
        }
        return null;
    }

    private class Node{ //анонимный класс
        int value;
        Node next;
        Node previous;

        @Override
        public String toString() {
            return String.format("%d", value);
        }

//        @Override
//        public int compareTo(Object o) {
//            Node node = (Node)o;
//            return Integer.compare(this.value, node.value);
//        }
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
