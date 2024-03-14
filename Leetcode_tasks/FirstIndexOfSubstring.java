
/**
 * 28. Find the Index of the First Occurrence in a String
 * Вернуть индекс первого вхождения подстроки needle в строку haystack. Если needle не является
 * подстрокой haystack, вернуть -1.
 */
public class FirstIndexOfSubstring {
    public int strStr(String haystack, String needle) {
        int index = -1;
        if (haystack.length() < needle.length()) return index;
        for (int i = 0; i < haystack.length(); i++) {
            boolean flag = true;
            if (haystack.charAt(i) == needle.charAt(0)) {
                index = i;
                int subIndex = i;
                for (int j = 0; j < needle.length(); j++) {
                    if (subIndex >= haystack.length()) index = -1;
                    else if (haystack.charAt(subIndex) != needle.charAt(j)) {
                        index = -1;
                        flag = false;
                        break;
                    }
                    subIndex++;
                }
                if (flag) return index;
            }
        }
        return index;
    }
}

