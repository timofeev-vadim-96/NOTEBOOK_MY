/**
 * 7. Reverse Integer
 *
 * Given a signed 32-bit integer x, return x with its digits reversed. If reversing x causes the value to go outside 
 * the signed 32-bit integer range [-231, 231 - 1], then return 0.
 */
public class ReverseInteger {
    public static void main(String[] args) {
        System.out.println(reverse(1020));
    }

    public static int reverse(int x) {
        if (x == 0) {
            return 0;
        }
        
        boolean isNegative = x < 0;

        String stringRepresentation = String.valueOf(x);
        int leftBoarder = 0;
        if (isNegative) {
            leftBoarder = 1;
        }

        StringBuilder sb = new StringBuilder();
        if (isNegative) {
            sb.append('-');
        }

        boolean flag = false;
        for (int i = stringRepresentation.length() - 1; i >= leftBoarder; i--) {
            if (!flag) {
                if (stringRepresentation.charAt(i) != '0') {
                    sb.append(stringRepresentation.charAt(i));
                    flag = true;
                }
            } else {
                sb.append(stringRepresentation.charAt(i));
            }
        }
        
        long longRepresentation = Long.parseLong(sb.toString());
        if (longRepresentation > Integer.MAX_VALUE || longRepresentation < Integer.MIN_VALUE) {
            return 0;
        } else {
            return Integer.parseInt(sb.toString());
        }
    }
}
