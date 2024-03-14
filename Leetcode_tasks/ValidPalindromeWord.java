/**
 * 125. Valid Palindrome
 * Дана строка. Нужно проверить на палиндром, при этом для проверки нужно удалить все символы, не являющиеся
 * буквой или цифрой.
 */

public class ValidPalindromeWord {
    public boolean isPalindrome(String s) {
        StringBuilder lowerCase = new StringBuilder(s.toLowerCase());
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < lowerCase.length(); i++) {
            if (Character.isLetterOrDigit(lowerCase.charAt(i))) res.append(lowerCase.charAt(i));
        }
        int i = 0;
        int j = res.length() - 1;
        while (j != 0 && i < res.length()) {
            if (res.charAt(i) != res.charAt(j)) return false;
            j--;
            i++;
        }
        return true;
    }
}

