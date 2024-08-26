import java.util.Arrays;

/**
 * 66. Plus One
 * You are given a large integer represented as an integer array digits, where each digits[i] is the ith digit of 
 * the integer. The digits are ordered from most significant to least significant in left-to-right order. 
 * The large integer does not contain any leading 0's.
 * Increment the large integer by one and return the resulting array of digits.
 */
public class PlusOne {
    public static void main(String[] args) {
        int[] arr = {1, 2, 9};
        System.out.println(Arrays.toString(plusOne(arr)));
    }

    public static int[] plusOne(int[] digits) {
        for (int i = digits.length - 1; i >= 0; i--) {
            if (digits[i] < 9) {
                digits[i]++;
                return digits;
            }
            if (i == 0) {
                int[] res = new int[digits.length + 1];
                res[0] = 1;
                return res;
            }
            if (digits[i] == 9) {
                digits[i] = 0;
            }
        }
        return digits;
    }
}
