
/**
 * 392.
 * Даны две строки s и t, возвращают, true если s это подпоследовательность из t, или false иначе.
 *
 * Подпоследовательность строки - это новая строка, которая формируется из исходной строки путем удаления
 * некоторых символов (может отсутствовать) без изменения относительного положения остальных символов.
 * (т.е. "ace" Является подпоследовательностью, "abcde" в то время как "aec" таковой не является).
 */
public class IsSubsequence {
    public static void main(String[] args) {
        String a = "asdf";
        String b = "";
        System.out.println(isSubsequence(a,b));
    }
    public static boolean isSubsequence(String s, String t) {
        if (s.isEmpty()) return true;
        else if (t.isEmpty()) return false;
        int charCounter = 0;
        for (int i = 0; i < t.length(); i++) {
            if (t.charAt(i) == s.charAt(charCounter)){
                charCounter++;
            }
            if (charCounter == s.length()) return true;
        }
        return false;
    }
}
