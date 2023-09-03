import java.util.ArrayList;
import java.util.List;

public class SimpleNumbers {
    public static void main(String[] args) {
        System.out.println(getSimpleNumbers(100));
        System.out.println(getSimpleNumbersOptimize(100));
    }

    //Классическое решение. Сложность O(n^2) 
    public static List<Integer> getSimpleNumbers (int untilNumb){ 
        List<Integer> simpleNumbers = new ArrayList<>();
        for (int i = 2; i <= untilNumb; i++) {
            boolean flag = true;
            for (int j = 2; j < i; j++) {
                if (i % j == 0)  {
                    flag = false;
                    break;
                }
            }
            if (flag) simpleNumbers.add(i);
        }
        return simpleNumbers;
    }
    //Оптимизированное решение. Во втором цикле доходим лишь до квадрата от i. Сложность O(n*sqrt(n)) 
    public static List<Integer> getSimpleNumbersOptimize (int untilNumb){ //n sqrt(n)
        List<Integer> simpleNumbers = new ArrayList<>();
        for (int i = 2; i <= untilNumb; i++) {
            boolean flag = true;
            for (int j = 2; j*j <= i; j++) { //по факту доходим до квадрата от i
                //хз как работает. в базовом виде убрать j*j на j < i
                if (i % j == 0)  {
                    flag = false;
                    break;
                }
            }
            if (flag) simpleNumbers.add(i);
        }
        return simpleNumbers;
    }
}
