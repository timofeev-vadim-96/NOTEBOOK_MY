/**
 * 1957. Delete Characters to Make Fancy String
 * 
 * A fancy string is a string where no three consecutive characters are equal.
 * Given a string s, delete the minimum possible number of characters from s to make it fancy.
 * Return the final string after the deletion. It can be shown that the answer will always be unique.
 */
public class FancyString {
    public static void main(String[] args) {
        System.out.println(makeFancyString("leeetcode"));
    }
    public static String makeFancyString(String s) {
        if (s.length() <= 2) {
            return s;
        }

        StringBuilder sb = new StringBuilder();
        sb.append(s.charAt(0));
        char previousCharacter = s.charAt(0);
        int counter = 1;

        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == previousCharacter) {
                if (counter < 2) {
                    sb.append(s.charAt(i));
                    counter++;
                }
            } else {
                sb.append(s.charAt(i));
                previousCharacter = s.charAt(i);
                counter = 1;
            }
        }

        return sb.toString();
    }
}
