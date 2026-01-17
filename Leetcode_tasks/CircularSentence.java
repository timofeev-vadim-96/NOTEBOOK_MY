
/**
 * 2490. Circular Sentence
 *
 * A sentence is circular if:
 * The last character of a word is equal to the first character of the next word.
 * The last character of the last word is equal to the first character of the first word.
 */
public class CircularSentence {
    public static void main(String[] args) {
        System.out.println(isCircularSentence("leetcode exercises sound delightful"));
    }

    public static boolean isCircularSentence(String sentence) {
        String [] arr = sentence.split(" ");
        char firstCharInTheFirstWord = arr[0].charAt(0);
        char lastCharInTheLastWord = arr[arr.length - 1].charAt(arr[arr.length - 1].length() - 1);
        if (firstCharInTheFirstWord != lastCharInTheLastWord) {
            return false;
        }

        char previousWordLastChar = arr[0].charAt(arr[0].length() - 1);
        for (int i = 1; i < arr.length; i++) {
            if (arr[i].charAt(0) != previousWordLastChar) {
                return false;
            } else {
                previousWordLastChar = arr[i].charAt(arr[i].length() - 1);
            }
        }

        return true;
    }
}
