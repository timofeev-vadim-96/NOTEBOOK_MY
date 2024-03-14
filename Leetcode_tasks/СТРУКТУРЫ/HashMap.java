//Хаш-мапа состоит из массива бакетов(нодов), а нода по стандарту - значение ентити (ключ+значение) и ссылка
//на следующую ноду
package СТРУКТУРЫ;
public class HashMap {

    class Basket{ //Связный список
        Node head;
        class Node{
            Entity entity;
            Node next;
        }

        public Integer find(int key){
            Node node = head;
            while (node != null){
                if (node.entity.key == key) return node.entity.value;
            }
            return null;
        }

        public boolean insert(Entity entity){
            Node node = new Node();
            node.entity = entity;
            if (head == null){
                head = node;
                return true;
            } else {
                Node currentNode = head;
                while (currentNode.next  != null) {
                    if (currentNode.entity.key == entity.key) return false; //если эл-т с таким ключом есть
                    currentNode = currentNode.next;
                }
                currentNode.next = node;
                return true;
            }
        }
    }

    class Entity{ //ключ+значение
        int key;
        int value;

        public Entity(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    public static int INIT_SIZE = 16;
    Basket[] baskets;

    public HashMap(int size) {
        this.baskets = new Basket[size];
    }

    public HashMap() {
        this(INIT_SIZE);
    }

    public int getIndex(int key){
//        return key.HashCode() //для не интовых
        return key % baskets.length;
    }

    public Integer find(int key){ //ищем значение по ключу
        int index = getIndex(key);
        Basket basket = baskets[index];
        if (basket != null) {
            return basket.find(key);
        }
        return null;
    }

    public boolean insert(int key, int value){
        Entity entity = new Entity(key, value);
        int index = getIndex(key);
        Basket basket = baskets[index];
        if (basket == null){
            basket = new Basket();
            baskets[index] = basket;
        }
        return basket.insert(entity);
    }
}
