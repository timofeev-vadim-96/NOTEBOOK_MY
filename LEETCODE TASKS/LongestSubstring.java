/**
 * 3. Longest Substring Without Repeating Characters (Medium)
 * Вернуть макс. длину подстроки, в кот. не повторяются символы
 */
import java.util.ArrayList;
import java.util.List;

public class LongestSubstring {
    public int lengthOfLongestSubstring(String s) {
        List<Character> characters = new ArrayList<>();
        int counter = 0;
        int maxCounter = 0;
        for (int i = 0; i < s.length(); i++) {
            boolean flag = true; //для определения вхождения символа в список символов
            char key = s.charAt(i); //текущий символ в строке
            for (int j = 0; j < characters.size(); j++) {
                if (characters.get(j) == key) { //проверяем наличие символа в списке символов
                    if (counter > maxCounter) maxCounter = counter;
                    counter = 0;
                    while (s.charAt(i-1) != characters.get(j)){ //проверяю предыдущий символ, чтобы откатить
                        // до крайнего вхождения повторившегося символа
                        i--; //откатываем индекс до прошлого вхождения
                    }
                    characters.clear();
                    i--; //чисто для нивелирования инкремента в цикле фор
                    flag = false;
                }
            }
            if (flag) {
                characters.add(key);
                counter++;
            }
        }
        if (counter > maxCounter) maxCounter = counter;
        return maxCounter;
    }
}
