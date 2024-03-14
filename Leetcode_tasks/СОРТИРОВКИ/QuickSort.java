//БЫСТРАЯ СОРТИРОВКА. Суть: определяем пивот посередине коллекци. Относительно этого пивота перемещаем
//все меньшие величины влево от него, а большие вправо. Когда left пересечется с right, рекурсивно выполняем
//тоже самое для правой и левой стороны, относительно пивота, пока left не достигнет правой границы, а right - левой
import java.util.Arrays;
import java.util.Random;

public class QuickSort {
    public static void main(String[] args) {
        int [] newArray = createArray(10, 1, 100);
        System.out.println(Arrays.toString(newArray));
        quickSort(newArray);
        System.out.println(Arrays.toString(newArray));
    }

    public static void quickSort(int [] inputArray) {
        quickSort(inputArray, 0, inputArray.length-1);
    }

    //Сложность O(n*log(n), n- т.к. внутри доп. применяем while
    public static void quickSort(int [] inputArray, int leftBoarder, int rightBoarder) {
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

    public static int [] createArray(int size, int leftBoarder, int rightBoarder){
        Random random = new Random();
        int [] newArray = new int[size];
        for (int i = 0; i < size; i++) {
            newArray[i] = random.nextInt(leftBoarder, rightBoarder+1);
        }
        return newArray;
    }
}