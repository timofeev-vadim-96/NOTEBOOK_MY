
/**
 * 1106. Parsing A Boolean Expression
 * 
 */
public class ParsingBooleanExpression {
    public static void main(String[] args) {
//        System.out.println(parseBoolExpr("!(&(f,t))"));
        System.out.println(parseBoolExpr("|(f,f,f,t)"));
    }

    public static boolean parseBoolExpr(String expression) {
        StringBuilder sb = new StringBuilder(expression);

        int i = sb.length() - 1;
        while (i >= 0) {
            char symb = sb.charAt(i);
            if (symb == '&' || symb == '|' || symb == '!') {
                int rightBoarder = findRightBoarder(i, sb.toString());
                String substring = sb.substring(i, rightBoarder + 1);
                String changedSubstring;
                if (sb.charAt(i) == '&') {
                    changedSubstring = substring.contains("f") ? "f" : "t";
                } else if (sb.charAt(i) == '|') {
                    changedSubstring = substring.contains("t") ? "t" : "f";
                } else {
                    changedSubstring = substring.contains("t") ? "f" : "t";
                }
                int tempLength = sb.length();
                sb.replace(i, rightBoarder + 1, changedSubstring);
                i = tempLength - substring.length();
            } else {
                i--;
            }
        }

        String result = sb.toString();
        return result.equals("t");
    }

    private static int findRightBoarder(int startIndex, String str) {
        for (int i = startIndex; i < str.length(); i++) {
            if (str.charAt(i) == ')') {
                return i;
            }
        }

        throw new RuntimeException("right boarder is not found");
    }
}
