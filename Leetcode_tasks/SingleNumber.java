import java.util.HashSet;
import java.util.Set;

/**
 * 136. Single Number
 *
 * Учитывая непустой массив целых чисел nums, каждый элемент появляется дважды, кроме одного.
 * Найдите этот единственный элемент.
 */
public class SingleNumber {
    public static void main(String[] args) {
        int[] ints = {4,1,2,1,2};

        System.out.println(singleNumber(ints));
    }

    public static int singleNumber(int[] nums) {
        int totalSum = 0;
        for (int n: nums) {
            totalSum+=n;
        }

        Set<Integer> uniques = new HashSet<>(nums.length);
        for (int n: nums) {
            uniques.add(n);
        }

        int uniquesSum = 0;
        for (int n: uniques) {
            uniquesSum+=n*2;
        }

        return uniquesSum - totalSum;
    }
}