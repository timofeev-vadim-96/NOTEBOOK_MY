
/**
 * 5. Longest Palindromic Substring
 * Учитывая строку s, верните самую длинную
 * палиндромная подстрока в s.
 */
public class LongestPalindromicSubstring {
    public static void main(String[] args) {
        System.out.println(longestPalindrome("abcdcba"));
    }

    public static String longestPalindrome(String s) {
        if (s.isEmpty()) return "";
        else if (s.length() == 1) return s;

        String parallelEven = parallelPalindrome(s, 0, 0, 1);
        String parallelOdd = parallelPalindrome(s, 1, -1, 1);

        if (parallelEven.length() >= parallelOdd.length()) return parallelEven;
        else return parallelOdd;
    }

    private static String parallelPalindrome(String s, int counterStartValue, int leftPointIncrement, int rightPointIncrement){
        int palindromeFirstIndex = 0;
        int maxPalindromeLength = 1;


        for (int i = 0; i < s.length(); i++) {
            int leftPoint;
            int rightPoint;
            int counter;

            leftPoint = i + leftPointIncrement;
            rightPoint = i + rightPointIncrement;
            counter = counterStartValue;

            while (leftPoint >= 0 && rightPoint < s.length()) {
                if (s.charAt(leftPoint) == s.charAt(rightPoint)) {
                    counter += 2;

                    if (maxPalindromeLength < counter) {
                        maxPalindromeLength = counter;
                        palindromeFirstIndex = leftPoint;
                    }
                    leftPoint--;
                    rightPoint++;
                } else break;
            }
        }
        return s.substring(palindromeFirstIndex, palindromeFirstIndex + maxPalindromeLength);
    }
}
