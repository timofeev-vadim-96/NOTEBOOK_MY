//БИНАРНЫЙ ПОИСК. Суть: каждую итерацию мы откидываем половину массива в поиске нужного значения. Поэтому,
//чтобы увеличить кол-во шагов на 1, нужно увеличить размер массива в два раза (очень выгодно). Работает ТОЛЬКО НА
//ОТСОРТИРОВАННЫХ КОЛЛЕКЦИЯХ. Мы, собственно и пользуемся свойством отсортированной коллекции
import java.util.Arrays;
import java.util.Random;

public class BinarySearch {
    public static void main(String[] args) {
        int [] newArray = createArray(15, 1, 10);
        Arrays.sort(newArray);
        System.out.println(Arrays.toString(newArray));
        System.out.println(binarySearch(newArray, 8));
    }

    //Бинарный поиск. Сложность: O(log(n))
    public static int binarySearch(int [] inputArray, int searchNumb, int leftBoarder, int rightBoarder){
        int trySearch = (rightBoarder+leftBoarder)/2;
        if (inputArray[trySearch] == searchNumb) return trySearch;
        else if (rightBoarder-leftBoarder == 0) return -1; //на случай, если искогомого в массиве нет
        else if (inputArray[trySearch] > searchNumb) return binarySearch(inputArray, searchNumb, leftBoarder,
                trySearch-1);//-1 т.к. саму серединку мы уже проверили
        else return binarySearch(inputArray, searchNumb, trySearch+1, rightBoarder); //+1 , потому что
        // серединку уже проверили
    }

    public static int binarySearch(int [] inputArray, int searchNumb){
        return binarySearch(inputArray, searchNumb, 0, inputArray.length-1);
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