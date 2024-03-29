
/**
 * 88. Merge Sorted Array
 * Даны два отсортированных массива по возрастанию. Первый массив имеет длину, необходимую для конечного результата
 * 1 массив заполнен на m элементов, остальные элементы нули. 2 массив имеет длину n
 * Необходимо замерджить два массива в первый по возрастанию элементов. 
 */

public class MergeTwoSortedArrays {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = 0; //итерация до m
        int j = 0; //итерация до n
        int k = 0; //для итерации по новому массиву
        int [] arr = new int[m+n];
        while (i < m && j < n){ //наполняем внутренний массив до момента, когда один из входных массивов
//            полностью не израсходуется
            if (nums1[i] <= nums2[j]) {
                arr[k] = nums1[i];
                i++;
            }
            else {
                arr[k] = nums2[j];
                j++;
            }
            k++;
        }
        while (i < m){ //допиливаем первый вх. массив, если не допилили
            arr[k] = nums1[i];
            i++;
            k++;
        }
        while (j < n){ //допиливаем второй вх. массив, если не допилили
            arr[k] = nums2[j];
            j++;
            k++;
        }
//        копируем внутренний массив в первый по условию
        System.arraycopy(arr, 0, nums1, 0, arr.length);
    }
}
