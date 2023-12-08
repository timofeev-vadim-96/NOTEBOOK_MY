import java.util.Stack;

/**
 * 20. Valid Parentheses
 * Проверить входящую строку, содержающую только скобки трех типов, на валидность
 */
public class ValidBrackets {
        public boolean isValid(String s) {
        Stack<Character> queue = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') queue.push(')');
            else if (s.charAt(i) == '{') queue.push('}');
            else if (s.charAt(i) == '[') queue.push(']');
            else if (queue.isEmpty() || s.charAt(i) != queue.pop()) return false;
        }
        return queue.isEmpty();
    }


    //медленное решение, но со вкусом
    public boolean isValid2(String s) {
        while (s.length() > 0) {
            if (s.contains("()")) {
                s = s.replace("()", "");
            } else if (s.contains("{}")) {
                s = s.replace("{}", "");
            } else if (s.contains("[]")) {
                s = s.replace("[]", "");
            } else return false;
        }
        return true;
    }
}
