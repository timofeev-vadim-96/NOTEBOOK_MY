
import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 232. Реализовать очередь "первый вход - первый выход" (FIFO), используя только два стека. Реализованная очередь должна
 * поддерживать все функции обычной очереди (push, peek, pop, и empty).
 */
public class ImplementsQueueUsingStacks {
    Deque<Integer> elements;
    public ImplementsQueueUsingStacks() {
        elements = new ArrayDeque<>();
    }

    /**
     * Помещает элемент x в конец очереди.
     * @param x элемент
     */
    public void push(int x) {
        elements.addLast(x);
    }

    /**
     * Удаляет элемент из начала очереди и возвращает его.
     */
    public int pop() {
        return elements.pop();
    }

    /**
     * Возвращает элемент в начале очереди.
     */
    public int peek() {
        return elements.getFirst();
    }

    /**
     * @return true если очередь пуста, false в противном случае.
     */
    public boolean empty() {
        return elements.isEmpty();
    }
}
