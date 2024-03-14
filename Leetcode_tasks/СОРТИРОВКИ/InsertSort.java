//СОРТИРОВКА ВСТАВКАМИ. Суть: при каждой итерации сравниваем i элемент со всеми остальными и меняем местами
//при необходимости. Таким образом, на позиции i в результате каждой итерации будет мин. элемент
//p.s. ИМХО, ухудшенная версия сортировки Выбором.
import java.util.Arrays;
import java.util.Random;

public class InsertSort {
    public static void main(String[] args) {
        int [] newArray = createArray(10, 1, 100);
        System.out.println(Arrays.toString(newArray));
        choiceSort(newArray);
        System.out.println(Arrays.toString(newArray));
    }

    //Сложность O(n^2)
    public static void choiceSort(int [] inputArray){
        int size = inputArray.length;
        int minElemPos;
        int tempValue;
        for (int i = 0; i < size-1; i++) { //последний элемент сравнивать ни с чем не нужно
            minElemPos = i; //с каждой итерацией устанавливаем по умолчанию мин элемент i
            for (int j = i+1; j < size; j++) {
                if (inputArray[j] < inputArray[minElemPos]) minElemPos = j;
            }
            if (i != minElemPos){ //если это не изначальный элемент то:
                tempValue = inputArray[i];
                inputArray[i] = inputArray[minElemPos];
                inputArray[minElemPos] = tempValue;
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