/**
 * 26. Remove Duplicates from Sorted Array
 * 
 * 1. Отсортировать входящий список nums с исключенными дубликатами внутри метода. 
 * 2. Вернуть количество уникальных элементов в этом списке (k).
 */
class RemoveDuplicatesFromSortedArr {
    public int removeDuplicates(int [] nums) {
        int [] buffer = new int[nums.length];
        int currentNumb = Integer.MIN_VALUE;
        int k = 0;
        for (int i = 0; i < nums.length; i++) {
            if (currentNumb != nums[i]) {
                currentNumb = nums[i];
                buffer[k++] = currentNumb;
            }
        }
        for (int i = 0; i < buffer.length; i++) {
            nums[i] = buffer[i];
        }
        return k;
    }
}
