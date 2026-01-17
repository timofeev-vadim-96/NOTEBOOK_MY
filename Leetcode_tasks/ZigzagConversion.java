/**
 * 6. Zigzag Conversion
 *
 * The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this:
 * (you may want to display this pattern in a fixed font for better legibility)
 * P   A   H   N
 * A P L S I I G
 * Y   I   R
 */
public class ZigzagConversion {
    public static void main(String[] args) {
        String f = convert("PAYPALISHIRING", 3);
        assert(f.equals("PAHNAPLSIIGYIR"));
    }

    public static String convert(String s, int numRows) {
        if (numRows == 1) {
            return s;
        }

        int columns = s.length() / 2 + s.length() % 2;
        char[][] arr = new char[numRows][columns];

        int rowIndex = 0;
        int columnIndex = 0;
        int index = 0;
        while (index < s.length()) {
            if (rowIndex == numRows) {
                rowIndex--;
                while (rowIndex != 0 && index < s.length()) {
                    char c = s.charAt(index++);
                    arr[--rowIndex][++columnIndex] = c;
                }
                rowIndex++;
            } else {
                char c = s.charAt(index++);
                arr[rowIndex++][columnIndex] = c;
            }
        }

        return collectChars(arr);
    }

    private static String collectChars(char[][] grid) {
        StringBuilder sb = new StringBuilder();
        for (char[] arr : grid) {
            for (char c : arr) {
                if (c != '\u0000') {
                    sb.append(c);
                }
            }
        }
        return sb.toString();
    }
}
