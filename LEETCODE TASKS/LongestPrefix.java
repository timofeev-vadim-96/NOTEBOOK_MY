/**
 * Leetcode: 14. Longest Common Prefix
 * Вернуть самый длинный общий префикс из массива строк. Если общего префикса нет - вернуть "".
 */
public class LongestPrefix {
    public String longestCommonPrefix(String[] strs) {
        String substring = "";
        int minLength = Integer.MAX_VALUE;
        for (String str : strs) {
            if (str.length() < minLength) minLength = str.length(); //мин длина строки для мин префикса
        }
        char currentChar;
        int i = 0;
        while (i < minLength) {
            currentChar = strs[0].charAt(i); //проверяем по-символьно каждую строку на соответствие символу в первой строке
            for (String str : strs) {
                if (str.charAt(i) != currentChar) return substring;
            }
            substring += currentChar;
            i++;
        }
        return substring;
    }
}