import java.util.HashMap;
import java.util.Map;

/**
 * 13. Roman to Integer
 */
public class RomanToInteger {
    private static final Map<Character, Integer> romanIntegerMap = new HashMap<>();

    public static void main(String[] args) {
        System.out.println(romanToInt("MCMXCIV"));
    }

    public static int romanToInt(String s) {
        fillRomanIntegerMap();

        int sum = 0;
        for (int i = 0; i < s.length(); i++) {
            char letter = s.charAt(i);
            if (i < s.length() - 1 &&
                    (letter == 'I' || letter == 'X' || letter == 'C') &&
                    checkIfTheNextLetterIsLarger(s, i)) {
                char nextLetter = s.charAt(i + 1);
                switch (letter) {
                    case 'I':
                        if (nextLetter == 'V') {
                            sum += 4;
                        } else if (nextLetter == 'X') {
                            sum += 9;
                        }
                        i++;
                        break;
                    case 'X':
                        if (nextLetter == 'L') {
                            sum += 40;
                        } else if (nextLetter == 'C') {
                            sum += 90;
                        }
                        i++;
                        break;
                    case 'C':
                        if (nextLetter == 'D') {
                            sum += 400;
                        } else if (nextLetter == 'M') {
                            sum += 900;
                        }
                        i++;
                        break;
                    default:
                        throw new RuntimeException("Unexpected");
                }
            } else {
                sum += romanIntegerMap.get(letter);
            }
        }
        return sum;
    }

    private static boolean checkIfTheNextLetterIsLarger(String s, int currentIndex) {
        char letter = s.charAt(currentIndex);
        char nextLetter = s.charAt(currentIndex + 1);
        switch (letter) {
            case 'I':
                if (nextLetter == 'V' || nextLetter == 'X') {
                    return true;
                }
            case 'X':
                if (nextLetter == 'L' || nextLetter == 'C') {
                    return true;
                }
            case 'C':
                if (nextLetter == 'D' || nextLetter == 'M') {
                    return true;
                }
            default:
                return false;
        }
    }

    private static void fillRomanIntegerMap() {
        romanIntegerMap.put('I', 1);
        romanIntegerMap.put('V', 5);
        romanIntegerMap.put('X', 10);
        romanIntegerMap.put('L', 50);
        romanIntegerMap.put('C', 100);
        romanIntegerMap.put('D', 500);
        romanIntegerMap.put('M', 1000);
    }
}
