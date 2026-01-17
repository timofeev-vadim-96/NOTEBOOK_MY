import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 202. Happy number
 * <p>
 * Напишите алгоритм, чтобы определить, является ли число n счастливым.
 * <p>
 * Счастливое число - это число, определенное следующим процессом:
 * <p>
 * Начиная с любого положительного целого числа, замените его суммой квадратов его цифр.
 * Повторяйте процесс до тех пор, пока число не станет равным 1 (и не останется на этом уровне) или не начнёт бесконечно повторяться в цикле, который не включает 1.
 * Те числа, для которых этот процесс заканчивается на 1, являются счастливыми.
 * Верните, true если n это счастливое число, и false если нет.
 */
public class HappyNumber {
    public static void main(String[] args) {
        System.out.println(isHappy(19));
    }

    public static boolean isHappy(int n) {
        Set<Integer> ints = new HashSet<>();

        while (n != 1 && !ints.contains(n)) {
            ints.add(n);
            String number = String.valueOf(n);

            int[] separatedNumbers = number.chars().map(Character::getNumericValue).toArray();

            n = Arrays.stream(separatedNumbers).map(i -> (int) Math.pow(i, 2)).sum();
        }

        return n == 1;
    }
}
