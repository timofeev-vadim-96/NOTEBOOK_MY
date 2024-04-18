
/**
 * 34. Find First and Last Position of Element in Sorted Array
 *
 * Учитывая массив целых чисел, nums отсортированных в неубывающем порядке, найдите начальную и конечную позиции
 * данного target значения.
 * Если target не найден в массиве, верните [-1, -1].
 *
 * Вы должны написать алгоритм со O(log n) сложностью во время выполнения.
 */
public class FindFirstAndLastPositionOfElementInSortedArray {
    public int[] searchRange(int[] nums, int target) {
        int[] ranges = {-1, -1};
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                if (ranges[0] == -1) {
                    ranges[0] = i;
                }
                ranges[1] = i;
            }
        }
        return ranges;
    }
}
