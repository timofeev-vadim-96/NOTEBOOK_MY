import java.util.Arrays;
import java.util.Random;

public class MergeSort {
    public static void main(String[] args) {
        int[] arr = createArray(10, 0, 25);
        System.out.println(Arrays.toString(arr));
        mergeSort(arr);
        System.out.println(Arrays.toString(arr));
    }


    public static int[] createArray(int size, int minValue, int maxValue) {
        Random random = new Random();
        int[] newArray = new int[size];
        for (int i = 0; i < size; i++) {
            newArray[i] = random.nextInt(minValue, maxValue + 1);
        }
        return newArray;
    }

    public static void mergeSort(int[] arr) { //перегрузка
        mergeSort(arr, 0, arr.length - 1);
    }

    //работает быстрее, если вынести общий буфер в глобальную переменную
    public static void mergeSort(int[] arr, int leftBoarder, int rightBoarder) {
        if (rightBoarder == leftBoarder) return;
        int pivot = (rightBoarder + leftBoarder) / 2;
        mergeSort(arr, leftBoarder, pivot); //уменьшение левых частей до размера = 1
        mergeSort(arr, pivot+1, rightBoarder); //уменьшение правых частей до размера = 2
        int[] buffer = new int[arr.length]; //буферный массив //если вынести 
        int i = leftBoarder;
        int j = pivot + 1;
        int k = leftBoarder; //индекс для буфера
        while (i <= pivot && j <= rightBoarder) {
            if (arr[i] < arr[j]) {
                buffer[k++] = arr[i++];
            } else buffer[k++] = arr[j++];
        }
        while (i <= pivot) { //если остались элементы в левой части - дозаливаем в буфер
            buffer[k++] = arr[i++];
        }
        while (j <= rightBoarder) { //если остались элементы в правой части - дозаливаем в буфер
            buffer[k++] = arr[j++];
        }
        for (int l = leftBoarder; l <= rightBoarder; l++) {
            arr[l] = buffer[l]; //заливаем буфер в основной массив
        }
    }
}