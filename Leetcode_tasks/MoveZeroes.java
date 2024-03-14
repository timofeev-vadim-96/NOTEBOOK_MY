/**
 * 283. Move Zeroes
 * Учитывая массив целых чисел, nums переместите все 0 в конец его, сохраняя относительный порядок ненулевых элементов.
 * <p>
 * Обратите внимание, что вы должны сделать это на месте, не создавая копию массива.
 */
public class MoveZeroes {
    public static void main(String[] args) {
        int[] ints = {13, 0, 0, 1, 4, 0, 0};
        moveZeroes(ints);
        System.out.println(Arrays.toString(ints));
    }

    public static void moveZeroes(int[] nums) {
        int LAST_ELEMENT_INDEX = nums.length - 1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                if (i == LAST_ELEMENT_INDEX) break;
                for (int k = i + 1; k < nums.length; k++) {
                    if (nums[k] != 0) {
                        int temp = nums[k];
                        nums[k] = nums[i];
                        nums[i] = temp;
                        break;
                    }
                }
            }
        }
    }
}