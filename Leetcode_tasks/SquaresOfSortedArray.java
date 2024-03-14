
import java.util.Arrays;

/**
 * 977. Squares Of Sorted Array
 * Учитывая массив целых чисел, nums отсортированных в неубывающем порядке, верните массив квадратов каждого числа,
 * отсортированных в неубывающем порядке.
 */
public class SquaresOfSortedArray {
    public static void main(String[] args) {
        int [] test = {-24, -22, -9, 0, 1,3,5};
        System.out.println(Arrays.toString(sortedSquares(test)));
    }
    public static int[] sortedSquares(int[] nums) {
        int[] resultArr = new int[nums.length];
        int positiveElementsPivot = Integer.MIN_VALUE;
        for (int i = nums.length - 1; i >= 0; i--) {
            if (nums[i] >= 0) positiveElementsPivot = i; //ищем где в массиве начинаются положительные числа
            nums[i] *= nums[i]; //тут же возводим их в квадрат
        }

        if (positiveElementsPivot == Integer.MIN_VALUE) { //если все элементы отрицательные - просто разворачиваю
            int resultArrIndex = 0;
            for (int i = nums.length - 1; i >= 0; i--) {
                resultArr[resultArrIndex++] = nums[i];
            }
        } else if (positiveElementsPivot == 0) return nums; //если отрицательных нет - возвращаем исходник
        else {
            int negativeElementPivot = positiveElementsPivot - 1;

            int resultArrIndex = 0;
            while (negativeElementPivot >= 0 && positiveElementsPivot < nums.length){
                if (nums[positiveElementsPivot] <= nums[negativeElementPivot]) {
                    resultArr[resultArrIndex++] = nums[positiveElementsPivot++];
                }
                else resultArr[resultArrIndex++] = nums[negativeElementPivot--];
            }

            while (negativeElementPivot >= 0){
                resultArr[resultArrIndex++] = nums[negativeElementPivot--];
            }

            while (positiveElementsPivot < nums.length){
                resultArr[resultArrIndex++] = nums[positiveElementsPivot++];
            }
        }
        return resultArr;
    }
}

