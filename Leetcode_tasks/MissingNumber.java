/**
 * 268. Учитывая массив, nums содержащий n различные числа в диапазоне [0, n], верните единственное число в диапазоне,
 * которое отсутствует в массиве.
 */
public class MissingNumber {
    public int missingNumber(int[] nums) {
        quickSort(nums);

        if (nums[0] != 0) return 0;

        for (int i = 1; i < nums.length; i++) {
            if ((nums[i] - nums[i-1]) > 1) return nums[i] - 1;
        }
        return nums.length;
    }

    private static void quickSort(int [] inputArray) {
        quickSort(inputArray, 0, inputArray.length-1);
    }

    //Сложность O(n*log(n), n- т.к. внутри доп. применяем while
    private static void quickSort(int [] inputArray, int leftBoarder, int rightBoarder) {
        int pivot = inputArray[(leftBoarder + rightBoarder) / 2];
        int left = leftBoarder;
        int right = rightBoarder;
        do {
            while (pivot > inputArray[left]) { //пока пивот больше arr[левый указатель] растим его
                left++;
            }
            while (inputArray[right] > pivot) { //пока пивот мньше arr[правый указатель] сокращаем его
                right--;
            }
            if (left <= right) {
                //если указатели пересеклись в пивоте, то, разумеется, менять местами ничего не нужно
                if (left < right) {
                    int temp = inputArray[left];
                    inputArray[left] = inputArray[right];
                    inputArray[right] = temp;
                }
                //Если границы еще не пересеклись, то двигаем их на 1
                left++;
                right--;
            }
        } while (left <= right); //цикл кончается, когда указатели переваливаются за пивот в противоположные
//        стороны
        if (left < rightBoarder) quickSort(inputArray, left, rightBoarder);
        if (right > leftBoarder) quickSort(inputArray, leftBoarder, right);
        //если левый и правый указатель дошли до границ массива, то массив отсортирован
    }
}
