import java.util.Arrays;
import java.util.Random;

public class PyramidSort {
    public static void main(String[] args) {
        int[] arr = createArray(100, 0, 100);
        System.out.println(Arrays.toString(arr));
        pyramidSort(arr);
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

    public static void pyramidSort(int [] arr){
        //Построение кучи (перегруппируем массив)
        for (int i = arr.length / 2 - 1; i >= 0 ; i--) {
            toPyramid(arr, arr.length, i);
        }

        //Один за другим извлекаем элементы из кучи
        for (int i = arr.length - 1; i >= 0 ; i--) {
            //Перемещаем текущий корень в конец
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            //toPyramid на уменьшенной куче
            toPyramid(arr, i, 0);
        }
    }

    //Восходящий алгоритм. Когда родитель должен быть больше детей
    private static void toPyramid(int[] arr, int heapSize, int rootIndex) { //rootIndex = 0
        int largest = rootIndex; //Инициализируем наибольший элемент как корень
        int leftChild = 2 * rootIndex + 1; //левый
        int rightChild = 2 * rootIndex + 2; //правый

        //Если левый дочерний элемент больше корня
        if (leftChild < heapSize && arr[leftChild] > arr[largest])
            largest = leftChild;
        if (rightChild < heapSize && arr[rightChild] > arr[largest])
            largest = rightChild;
        //Если самый большой элемент по результатам сравнения с детьми оказался не корень
        if (largest != rootIndex) {
            int temp = arr[rootIndex];
            arr[rootIndex] = arr[largest];
            arr[largest] = temp;

            //Рекурсивно преобразуем в бинарную кучу затронутое поддерево
            toPyramid(arr, heapSize, largest);
        }
    }
}