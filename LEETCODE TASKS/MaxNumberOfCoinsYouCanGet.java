
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 1561. Maximum Number of Coins You Can Get
 * Дан массив размеров 3n. каждый элемент массива это количество монет. За один раз мы берем 3 такие стопки монет.
 * Самая большая достается Алисе. Вторая - нам. Третья - Бобу. Нужно распределять кучи так, чтобы в итоге у нас
 * было максимально возможно по такому алгоритму количесво монет.
 */
public class MaxNumberOfCoinsYouCanGet {
    public int maxCoins(int[] piles) { //размер массива всегда кратен 3 (трем) по условию
        List<Integer> list = new ArrayList<>();
        int res = 0;
        for (int numb:piles) {
            list.add(numb);
        }
        Collections.sort(list);
        int iterations = list.size()/3; //количество раз, сколько мы будем выбирать себе кучу из общего количества куч монет
        int i = list.size()-2; //начинаем со второй с конца в отсортированном списке (1 - Алисе)
        for (int j = 0; j < iterations; j++) {
            res+= list.get(i);
            i-=2;
        }
        return res;
    }
}

