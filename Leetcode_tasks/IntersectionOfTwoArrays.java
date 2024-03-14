import java.util.ArrayList;
import java.util.Arrays;

/**
 * 350.
 * Учитывая два массива целых чисел nums1 и nums2, верните массив их пересечения. Каждый элемент в результате должен
 * появляться столько раз, сколько он отображается в обоих массивах, и вы можете возвращать результат в любом порядке.
 */
public class IntersectionOfTwoArrays {
       public static void main(String[] args) {
        int[] arr1 = {1,2};
        int[] arr2 = {1,1};
        System.out.println(Arrays.toString(intersect(arr1, arr2)));
    }

    public static int[] intersect(int[] nums1, int[] nums2) {
        int[] map1 = new int[findMaxValue(nums1) + 1];
        int[] map2 = new int[findMaxValue(nums2) + 1];
        countQuantityOfDuplicates(nums1, map1);
        countQuantityOfDuplicates(nums2, map2);

        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < map1.length; i++) {
            if (map2.length <= i) break;
            if (map1[i] == 0) continue;
            int minQuantityOfElements = map1[i];
            if (minQuantityOfElements > map2[i]) minQuantityOfElements = map2[i];
            for (int j = 0; j < minQuantityOfElements; j++) {
                list.add(i);
            }
        }

        int[] result = new int[list.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = list.get(i);
        }

        return result;
    }

    private static void countQuantityOfDuplicates(int[] source, int[] destination) {
        for (int numb : source) {
            destination[numb]++;
        }
    }

    private static int findMaxValue(int[] nums) {
        if (nums.length == 0) return 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < nums[i - 1]) {
                int temp = nums[i];
                nums[i] = nums[i - 1];
                nums[i - 1] = temp;
            }
        }
        return nums[nums.length - 1];
    }
}
