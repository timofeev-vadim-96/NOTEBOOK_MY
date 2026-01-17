/**
 * 796. Rotate String
 *
 * Given two strings s and goal, return true if and only if s can become goal after some number of shifts on s.
 * A shift on s consists of moving the leftmost character of s to the rightmost position.
 * For example, if s = "abcde", then it will be "bcdea" after one shift.
 */
public class RotateString {
    public static void main(String[] args) {
        System.out.println(rotateString("abcde", "abced"));
    }

    public static boolean rotateString(String s, String goal) {
        if (s.length() != goal.length()) {
            return false;
        }

        char[] chars = s.toCharArray();
        for (int i = 0; i < s.length(); i++) {
            char temp = chars[0];
            for (int j = 1; j < s.length(); j++) {
                chars[j-1] = chars[j];
            }
            chars[s.length() - 1] = temp;
            s = new String(chars);
            if (s.equals(goal)) {
                return true;
            }
        }
        return false;
    }
}