
/**
 * 35. Search Insert Position
 * Дан отсортированный массив уникальных чисел (ИМХО не имеет значения) и искомое число. Найти индекс этого числа или
 * индекс, куда его необходимо вставить при его отсутствии.
 */
public class SearchInsertPosition {

    public static int binarySearch(int[] inputArray, int searchNumb, int leftBoarder, int rightBoarder) {
        int trySearch = (rightBoarder + leftBoarder) / 2;
        if (inputArray[trySearch] == searchNumb) return trySearch;
        else if (rightBoarder - leftBoarder == 0 || rightBoarder - leftBoarder == 1){ //условия для четного и
            // нечетного количества элементов в массиве
            if (rightBoarder - leftBoarder == 0) {
                if (inputArray[trySearch] < searchNumb) return trySearch + 1;
                else return trySearch;
            }
            else {
                if (inputArray[leftBoarder] > searchNumb) return leftBoarder; //чтобы обратно не раздвувать массив
                //когда искомый элемент отсутствует и меньше левой границы
                else if (inputArray[rightBoarder] < searchNumb) return rightBoarder + 1; //чтобы обратно не раздвувать
                    // массив когда искомый элемент отсутствует и больше правой границы
                else if (inputArray[trySearch] > searchNumb) return binarySearch(inputArray, searchNumb, leftBoarder,
                        trySearch - 1); //если из двух оставшихся элементов еще может подойти левый
                else return binarySearch(inputArray, searchNumb, trySearch + 1, rightBoarder);
                //если из двух оставшихся элементов еще может подойти правый
            }
        }
        else if (inputArray[trySearch] > searchNumb) return binarySearch(inputArray, searchNumb, leftBoarder,
                trySearch - 1);//-1 т.к. саму серединку мы уже проверили
        else return binarySearch(inputArray, searchNumb, trySearch + 1, rightBoarder); //+1 , потому что
        // серединку уже проверили
    }

    public static int searchInsert(int[] nums, int target) {
        return binarySearch(nums, target, 0, nums.length - 1);
    }
}
