//ПУЗЫРЬКОВАЯ СОРТИРОВКА. Суть: сравниваем соседние элементы, и если левый меньше правого - меняем местами.
import java.util.Arrays;
import java.util.Random;

public class BubbleSort {
    public static void main(String[] args) {
        int [] newArray = createArray(10, 1, 100);
        System.out.println(Arrays.toString(newArray));
        bubbleSort(newArray);
        System.out.println(Arrays.toString(newArray));
    }

    //Пузырьковая сортировка. Сложность O(n^2)
    public static void bubbleSort(int [] inputArray){
        int size = inputArray.length;
        int temp;
        for (int i = 0; i < size; i++) {
            for (int j = 1; j < size-i; j++) {
                if (inputArray[j-1] > inputArray[j]){
                    temp = inputArray[j];
                    inputArray[j] = inputArray[j-1];
                    inputArray[j-1] = temp;
                }
            }
        }
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