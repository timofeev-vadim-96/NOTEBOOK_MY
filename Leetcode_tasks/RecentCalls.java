
/**
 * 933. Number of Recent Calls
 * У вас есть RecentCounter класс, который подсчитывает количество недавних запросов за определенный промежуток времени.
 *
 * Реализовать класс RecentCounter:
 *
 * RecentCounter() Инициализирует счетчик нулевыми недавними запросами.
 * int ping(int t) Добавляет новый запрос во времени, tгде t представляет некоторое время в миллисекундах, и
 * возвращает количество запросов, которые произошли за последние 3000 миллисекунды (включая новый запрос).
 * В частности, возвращает количество запросов, которые произошли во включающем диапазоне [t - 3000, t].
 * Гарантируется, что при каждом вызове в ping используется строго большее значение t, чем при предыдущем вызове.
 */
public class RecentCalls {
    private int [] pings;
    private int totalCounter;
    public RecentCalls() {
        pings = new int[10_000];
        totalCounter = 0;
    }

    public int ping(int t) {
        pings[totalCounter++] = t;
        int counter = 0;
        for (int i = totalCounter - 1; i >= 0; i--) {
            if (pings[i] >= t - 3000) counter++;
            else return counter;
        }
        return counter;
    }
}
