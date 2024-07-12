/**
 * 58. Length of Last Word
 * Учитывая строку, s состоящую из слов и пробелов, верните длину последнего слова в строке.
 */
public class LengthOfLastWord {
    public static void main(String[] args) {
        System.out.println(lengthOfLastWord("a"));
    }
    public static int lengthOfLastWord(String s) {
        int lastWordLengthCounter = 0;
        boolean isWordExists = false;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == ' ') {
                //если найден пробел тогда, когда были уже буквы
                if (isWordExists) return lastWordLengthCounter;
                else continue;
            }
            else {
                if (!isWordExists) isWordExists = true;
                lastWordLengthCounter++;
            }
        }
        return lastWordLengthCounter;
    }
}