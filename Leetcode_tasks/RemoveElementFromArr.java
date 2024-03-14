
/**
 * 27. Remove Element
 * 
 * Удалить элемент val из массива nums. Вернуть кол-во элементов без nums.
 */
public class RemoveElementFromArr {
    public int removeElement(int[] nums, int val) {
        int [] arr = new int[nums.length];
        int k = 0;
        for (int num : nums) {
            if (num != val) {
                arr[k++] = num;
            }
        }
        System.arraycopy(arr, 0, nums, 0, nums.length);
        return k;
    }
}